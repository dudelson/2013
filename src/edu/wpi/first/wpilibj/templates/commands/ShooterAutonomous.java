/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author robot
 */

/*
 * DRIVING:
 * 1s @ .5 power => 3' 4"
 * 
 * TURNING:
 * 1s @ .5 power => 30deg
 * 
 * 12v => 83% power 
 */
public class ShooterAutonomous extends CommandBase {
    /* This is the default autonomous command, to be used when the robot
     * is starting partially outside of the autozone and is allowed 3 disks.
     */
    private static final double POWER_CONSTANT = 10.0; 
    /* The power constant scales with the battery voltage to provide more accurate
     * shots at lower voltages. It it obtained by selecting the relationship between
     * output power and full voltage:
     *      PK / v = P_out
     * With a full battery (v = 12.0) and a selected benchmark power output (say .75):
     *      PK / 12.0 = .75
     *      PK = .75 * 12.0 = 9.0
     * So in this case the power constant becomes 9.0. Full power will be reached 
     * at 9 volts.
     */
    private int numShots; //the number of shots the autonomous program takes
    private boolean isFromLeft; //true if the robot starts to the left of the ladder, false otherwise (from right)
    
    private boolean isFinished; //passed to isFinished() as true once we're done
    
    public ShooterAutonomous() {
        this(3, true);
    }
    
    public ShooterAutonomous(int numShots, boolean isFromLeft) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
        requires(shooter);
        
        this.numShots = numShots;
        this.isFromLeft = isFromLeft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //activate the shooter first so that it's up to speed
        //by the time we need to shoot
        shooter.turnOn();
        double power = POWER_CONSTANT / DriverStation.getInstance().getBatteryVoltage();
        shooter.setSpeed(power);
        SmartDashboard.putNumber("Requested shooter speed (auto): ", power);
        //drive to the shooting position
        driveTrain.driveStraight(0.5);
        Timer.delay(1.3);
        driveTrain.stop();
        Timer.delay(1.5);
        if (isFromLeft) {
            driveTrain.turnRight(0.5);
        } else {
            driveTrain.turnLeft(0.5);
        }     
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(2.0);
        //FIRE!
        for (int i=0; i<numShots; i++) {
            //update the shooter speed each loop
            power = POWER_CONSTANT / DriverStation.getInstance().getBatteryVoltage();
            shooter.setSpeed(power);
            SmartDashboard.putNumber("Requested shooter speed (auto): ", power);
            shooter.activateFrisbeeFeeder();
            Timer.delay(2.0);
            shooter.resetFrisbeeFeeder();
            Timer.delay(1.0);
        }
        //end the command
        shooter.turnOff();
        isFinished = true;
        /* drive in a square test code
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        isFinished = true;
        */
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
