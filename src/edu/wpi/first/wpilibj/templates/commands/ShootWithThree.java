/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

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
 */
public class ShootWithThree extends CommandBase {
    /* This is the default autonomous command, to be used when the robot
     * is starting partially outside of the autozone and is allowed 3 disks.
     */
    //TODO: MAKE THIS DO SOMETHING OTHER THAN DRIVE IN A SQUARE.
    
    private boolean isFinished; //passed to isFinished() as true once we're done
    
    public ShootWithThree() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
        requires(shooter);
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
        shooter.setSpeed(1.0);
        //drive to the shooting position
        driveTrain.driveStraight(0.5);
        Timer.delay(1.3);
        driveTrain.stop();
        Timer.delay(1.5);
        driveTrain.turnLeft(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(2.0);
        //FIRE!
        shooter.activateFrisbeeFeeder();
        Timer.delay(2.0);
        shooter.activateFrisbeeFeeder();
        Timer.delay(2.0);
        shooter.activateFrisbeeFeeder();
        Timer.delay(2.0);
        //end the command
        shooter.setSpeed(0.0);
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
