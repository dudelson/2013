/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.templates.OI;

/**
 *
 * @author robot
 */
public class RunShooter extends CommandBase {
    //the speed we want the shooter to run at (must be between -1.0 and 1.0)
    private double requestedSpeed;
    
    public RunShooter() {
        //reserve the shooter
        requires(shooter);
        //shooter is initially not spinning
        requestedSpeed = 0.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //b toggles the shooter on/off
        if (oi.isButtonPressed(OI.XBOX_BUTTON_B)) {
            if (shooter.isOn()) {
                shooter.turnOff();
                System.out.println("Shooter: OFF");
            }
            else {
                shooter.turnOn();
                System.out.println("Shooter: ON");
            }
        }
        
        //the left/right bumpers decrement/increment the speed, respectively
        if (oi.isButtonPressed(OI.XBOX_BUTTON_LEFT_BUMPER)) requestedSpeed -= 0.1;
        else if (oi.isButtonPressed(OI.XBOX_BUTTON_RIGHT_BUMPER)) requestedSpeed += 0.1;
        
        //y outputs the requested speed and compares it to the actual speed
        if (oi.isButtonPressed(OI.XBOX_BUTTON_Y)) {
            System.out.println("Requested Speed: " + requestedSpeed + " Actual Speed: " + shooter.getSpeed());
        }
        
        //set the shooter to the requested speed
        shooter.setSpeed(requestedSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
