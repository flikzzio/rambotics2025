package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    private final SparkMax motor1 = new SparkMax(1, MotorType.kBrushless);
    private final SparkMax motor2 = new SparkMax(2, MotorType.kBrushless);
    private final RelativeEncoder encoder;
    private final DigitalInput limitSwitch = new DigitalInput(0);
    private final PIDController pidController = new PIDController(0.1, 0, 0);
    
    private static final double MAX_HEIGHT = 50.0; // Example max height in inches
    private static final double MIN_HEIGHT = 0.0;
    private static final double MAX_OUTPUT = 1.0;
    private static final double MIN_OUTPUT = -1.0;

    public Elevator() {
        encoder = motor1.getEncoder();
        pidController.setTolerance(0.5);
    }
    
    public void setElevatorPosition(double setpoint) {
        setpoint = Math.max(MIN_HEIGHT, Math.min(MAX_HEIGHT, setpoint));
        double output = pidController.calculate(encoder.getPosition(), setpoint);
        output = Math.max(MIN_OUTPUT, Math.min(MAX_OUTPUT, output));
        
        motor1.set(output);
        motor2.set(-output); // Manually setting both motors to the same output
        
        // Logging
        SmartDashboard.putNumber("Elevator Setpoint", setpoint);
        SmartDashboard.putNumber("Elevator Position", encoder.getPosition());
        SmartDashboard.putNumber("Elevator Output", output);
    }
    
    public void resetEncoder() {
        if (!limitSwitch.get()) { // If switch is pressed
            encoder.setPosition(0.0);
            SmartDashboard.putString("Encoder Reset", "Limit switch activated");
        }
    }
    
    @Override
    public void periodic() {
        resetEncoder();
        SmartDashboard.putBoolean("Limit Switch Pressed", !limitSwitch.get());
    }
}
