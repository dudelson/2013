/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.Team1512Joystick;

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
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //shooter is initially not spinning
        requestedSpeed = 0.0;
        //frisbee feeder is initially reset
        shooter.resetFrisbeeFeeder();
        //reset and start the counter
        shooter.resetCounter();
        shooter.startCounter();
        //System.out.println("Starting with frisbee feeder reset");
        //the shooter is initially off
        SmartDashboard.putString("Shooter: ", "OFF");       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //X toggles the shooter on/off
        if (OI.xbox2.isButtonPressed(Team1512Joystick.XBOX_BUTTON_X)) {
            if (shooter.isOn()) {
                shooter.turnOff();
                //write the state of the shooter to the Smart Dashboard
                SmartDashboard.putString("Shooter: ", "OFF");
                //System.out.println("Shooter Off");
                //so shooter always starts from zero when turned on.
                requestedSpeed = 0.0;
            }
            else {
                shooter.turnOn(); 
                //write the state of the shooter to the Smart Dashboard
                //System.out.println("Shooter On");
                SmartDashboard.putString("Shooter: ", "ON");
            }
        }
        
        //Y/A on xbox 2 increment/decrement the speed, respectively
        if (OI.xbox2.isButtonPressed(Team1512Joystick.XBOX_BUTTON_A) && requestedSpeed >= 0.0 && shooter.isOn()) 
        {
            requestedSpeed -= 0.1;
            //System.out.println("Down requestedSpeed=" + Double.toString(requestedSpeed));
        }
        else if (OI.xbox2.isButtonPressed(Team1512Joystick.XBOX_BUTTON_Y) && requestedSpeed <= 1.0 && shooter.isOn()) 
        {
            requestedSpeed += 0.1;
            //System.out.println("Up requestedSpeed=" + Double.toString(requestedSpeed));
        }
        //We never want shooter to go backwards
        if (requestedSpeed < 0.0) requestedSpeed = 0.0;
        //or go faster than the maximum
        if (requestedSpeed > 1.0) requestedSpeed = 1.0;
        
        //set the shooter to the requested speed
        if (shooter.isOn()) shooter.setSpeed(requestedSpeed); //fastest shooter speed is -1.0
        
        //Feed a frisbee into the shooter using B button  
        //feedFrisbee() returns a boolean indicating success (frisbee was fed)
        //if feedFrisbee() returns false, this means the feeder did not complete its full range of
        //motion and needs to be reset
        if (OI.xbox2.isButtonPressed(Team1512Joystick.XBOX_BUTTON_B) && shooter.isOn()) {
            if (!shooter.feedFrisbee()) shooter.resetFrisbeeFeeder();
        }

        //write the data to SmartDashboard
        SmartDashboard.putNumber("Requested Speed: ", requestedSpeed);
        SmartDashboard.putNumber("Actual Speed: ", shooter.getSpeed());
        SmartDashboard.putNumber("Distance (ft.): ", shooter.getUltrasonicDist());
        SmartDashboard.putNumber("Actual Speed (rpm): ", shooter.getCounterRPM());
        SmartDashboard.putNumber("Rev: ", shooter.getCounterRev());
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
