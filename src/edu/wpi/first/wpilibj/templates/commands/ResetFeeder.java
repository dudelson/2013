/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author robot
 */
public class ResetFeeder extends CommandBase {
    private boolean isFinished;
    
    public ResetFeeder() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(feeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!feeder.getLimStop()) {
            feeder.setForward();
        } else {
            feeder.turnOff();
            isFinished = true;
        }
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
