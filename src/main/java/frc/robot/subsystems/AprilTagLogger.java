package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AprilTagLogger {
    private final NetworkTable limelightTable;

    public AprilTagLogger() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void logAprilTagData() {
        // Retrieve AprilTag ID
        NetworkTableEntry tidEntry = limelightTable.getEntry("tid");
        int aprilTagID = (int) tidEntry.getDouble(-1); // Default to -1 if no tag is detected

        // Retrieve X, Y, and area of the tag
        double x = limelightTable.getEntry("tx").getDouble(0.0);
        double y = limelightTable.getEntry("ty").getDouble(0.0);
        double area = limelightTable.getEntry("ta").getDouble(0.0);

        // Retrieve full bot pose (position of robot relative to the field)
        double[] botpose = limelightTable.getEntry("botpose").getDoubleArray(new double[6]);

        // Post to SmartDashboard
        SmartDashboard.putNumber("AprilTag ID", aprilTagID);
        SmartDashboard.putNumber("Limelight X", x);
        SmartDashboard.putNumber("Limelight Y", y);
        SmartDashboard.putNumber("Limelight Area", area);

        // Log robot position if detected
        if (botpose.length == 6) {
            SmartDashboard.putNumber("Robot X", botpose[0]);
            SmartDashboard.putNumber("Robot Y", botpose[1]);
            SmartDashboard.putNumber("Robot Z", botpose[2]);
            SmartDashboard.putNumber("Robot Roll", botpose[3]);
            SmartDashboard.putNumber("Robot Pitch", botpose[4]);
            SmartDashboard.putNumber("Robot Yaw", botpose[5]);
        }
    }
}
