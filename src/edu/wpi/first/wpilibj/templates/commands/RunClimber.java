/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;

/**
 *
 * @author robot
 */
public class RunClimber extends CommandBase {
    //the speed we want the shooter to run at (must be between -1.0 and 1.0)
    private double requestedSpeed;
    
    public RunClimber() {
        //reserve the shooter
        requires(climber);
        //shooter is initially not spinning
        requestedSpeed = 0.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //the shooter is initially off
        SmartDashboard.putString("Climber: ", "OFF");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //b toggles the shooter on/off
        if (oi.isButtonPressed(OI.XBOX_BUTTON_Y)) {
            if (climber.isOn()) {
                climber.turnOff();
                //write the state of the shooter to the Smart Dashboard
                SmartDashboard.putString("Climber: ", "OFF");
                System.out.println("Climber off");
            }
            else {
                climber.turnOn();
                //write the state of the shooter to the Smart Dashboard
                SmartDashboard.putString("Climber: ", "ON");
                 System.out.println("Climber on");
           }
        }
        
        //the left/right bumpers decrement/increment the speed, respectively
        if (oi.isButtonHeldDown(OI.XBOX_BUTTON_BACK)) 
            {
                requestedSpeed -= 0.1; //old method decrement speed variable
                climber.setSpeed(-1.0); //new method - move full down while pressed
            }
        else if (oi.isButtonHeldDown(OI.XBOX_BUTTON_START)) 
            {
                requestedSpeed += 0.1; //old method increment speed variable
                  climber.setSpeed(1.0); //new method - move fullspeed up while pressed
          }
        else
        {
              climber.setSpeed(0.0);//new method - else don't move
        }
        
        //set the shooter to the requested speed
      //
      //Old method using requestedspeed variable  climber.setSpeed(requestedSpeed);
        
        //write the data to SmartDashboard
        SmartDashboard.putNumber("Climb Requested Speed", requestedSpeed);
        SmartDashboard.putNumber("Climb Actual Speed", climber.getSpeed());
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
