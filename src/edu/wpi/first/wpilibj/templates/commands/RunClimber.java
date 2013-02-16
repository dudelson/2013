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
public class RunClimber extends CommandBase {
    /* The climber class is controlled by xbox #1.
     * This class is very similar to RunShooter with the exception of
     * the victor speed, which is always either 1.0 or -1.0
     */
    
    public RunClimber() {
        //reserve the climber
        requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //the climber is initially off
        SmartDashboard.putString("Climber: ", "OFF");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //y toggles the climber on/off
        if (OI.xbox1.isButtonPressed(Team1512Joystick.XBOX_BUTTON_Y)) {
            if (climber.isOn()) {
                climber.turnOff();
                //write the state of the climber to the Smart Dashboard
                SmartDashboard.putString("Climber: ", "OFF");
                //System.out.println("Climber off");
            } else {
                climber.turnOn();
                //write the state of the shooter to the Smart Dashboard
                SmartDashboard.putString("Climber: ", "ON");
                //System.out.println("Climber on");
           }
        }
        
        //A toggles climber up/down
        if (OI.xbox1.isButtonPressed(Team1512Joystick.XBOX_BUTTON_A) && climber.isOn()) {
            climber.moveDown(); //new method - move full down while pressed
            //write the current state to the SmartDashboard
            SmartDashboard.putString("Climber: ", "DOWN");
        } else if (OI.xbox1.isButtonPressed(Team1512Joystick.XBOX_BUTTON_A) && !climber.isOn()) {
            climber.moveUp();  //new method - move fullspeed up while pressed
            //write the current state to the SmartDashboard
            SmartDashboard.putString("Climber: ", "UP");
        } else {
            climber.setSpeed(0.0);  //new method - else don't move
            //write the current state to the SmartDashboard
            SmartDashboard.putString("Climber: ", "ON");
        }
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
