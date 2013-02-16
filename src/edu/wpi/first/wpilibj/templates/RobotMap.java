package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int leftDriveMotor = 1;
    public static final int rightDriveMotor = 4;
    
    public static final int shooterVictor = 5;
    public static final int shooterAngleVictor = 7; //CHANGE TO ACTUAL VALUE LATER
    public static final int feederServo1 = 10;
    public static final int feederServo2 = 9;
    
    public static final int climberVictor = 6;
    
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
