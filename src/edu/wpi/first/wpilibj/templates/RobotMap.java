package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

/* ULTRASONIC SENSOR DATA:
 *     10ft. => 1.174v - 1.184v (avg. 1.179)
 *     5ft.  => 0.567v - 0.577v (avg. 0.572)
 */
public class RobotMap {
    //analog module
    public static final int ultrasonic_sensor = 3;
    
    //digital module
    public static final int quad_encoder_A  = 1;
    public static final int quad_encoder_B  = 2;
    public static final int feeder_lim_stop = 11;
    public static final int feeder_lim_bump = 12;
    public static final int climber_lim_up  = 13;
    public static final int climber_lim_down= 14;
    
    //drive subsystem
    public static final int leftDriveMotor  = 1;
    public static final int rightDriveMotor = 4;
    
    //shooter subsystem
    public static final int shooterVictor   = 5;   
    
    //climber subsystem
    public static final int climberVictor   = 6;
    
    //feeder subsystem
    public static final int feederRelay     = 1;
    
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}
