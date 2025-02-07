/* package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

public class SetElevatorPositionCommand extends Command {
    private final Elevator elevator;
    private final double setpoint;

    public SetElevatorPositionCommand(Elevator elevator, double setpoint) {
        this.elevator = elevator;
        this.setpoint = setpoint;
        addRequirements(elevator);
    }

    @Override
    public void execute() {
        elevator.setElevatorPosition(setpoint);
    }

    // @Override
    // public boolean isFinished() {
        // return Math.abs(elevator.getEncoderPosition() - setpoint) < 0.5;
    }
}
*/