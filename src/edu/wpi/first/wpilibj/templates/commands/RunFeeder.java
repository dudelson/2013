/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.Team1512Joystick;

/**
 *
 * @author robot
 */
public class RunFeeder extends CommandBase {
    private boolean isFinished;
    
    public RunFeeder() {
        requires(feeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
        if (feeder.getLimStop()) {
            System.out.println("[Feeder] Feeding...");
            feeder.setBackward();
            Timer.delay(0.1);
        } else {
            System.out.println("[Feeder] Canceled!");
            new ResetFeeder().start();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //stop
        if (feeder.getLimStop()) {
            feeder.turnOff();
            isFinished = true;
        //bump
        } else if (feeder.getLimBump()) {
            feeder.turnOff();
            Timer.delay(0.1);
            feeder.setForward();
        }
        
        SmartDashboard.putBoolean("Stop Limit: ", feeder.getLimStop());
        SmartDashboard.putBoolean("Bump Limit: ", feeder.getLimBump());
//        SmartDashboard.putString("Feeder: ", feeder.getState());
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
        feeder.turnOff();
    }
}
