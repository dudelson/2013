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
    private boolean runFeeder;
    
//    private ResetFeeder reset;
    
    public RunFeeder() {
        requires(feeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        runFeeder = false;
//        reset = new ResetFeeder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (OI.xbox2.isButtonPressed(Team1512Joystick.XBOX_BUTTON_B)) {
            feeder.setForward();
            Timer.delay(0.2);
            runFeeder = true;
        }
        if (runFeeder) {
            //stop
            if (feeder.getLimStop()) {
//                System.out.println("Stop");
                feeder.turnOff();
                runFeeder = false;
            //bump
            } else if (feeder.getLimBump()) {
//                System.out.println("bump");
                feeder.turnOff();
                Timer.delay(0.1);
                feeder.setBackward();
            }

//            SmartDashboard.putBoolean("Stop Limit: ", feeder.getLimStop());
//            SmartDashboard.putBoolean("Bump Limit: ", feeder.getLimBump());
//        SmartDashboard.putString("Feeder: ", feeder.getState());
        }
        
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        runFeeder = false;
        feeder.turnOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        runFeeder = false;
        feeder.turnOff();
//        reset.cancel();
    }
}
