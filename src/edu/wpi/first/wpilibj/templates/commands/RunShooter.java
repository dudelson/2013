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
        //reset and start the counter
        shooter.resetCounter();
        shooter.startCounter();
        //System.out.println("Starting with frisbee feeder reset");
        //the shooter is initially off
        SmartDashboard.putString("Shooter: ", "OFF");       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (!SmartDashboard.getBoolean("SHOOTER ON/OFF: ") && shooter.isOn()) {
            shooter.turnOff();
            SmartDashboard.putString("Shooter: ", "OFF");
        } else if (SmartDashboard.getBoolean("SHOOTER ON/OFF: ") && !shooter.isOn()) {
            shooter.turnOn();
            SmartDashboard.putString("shooter: ", "ON");
        }
        
        double speed = SmartDashboard.getNumber("SET SHOOTER SPEED TO: ");
        if (speed >= 0.0 && speed <= 1.0) {
            shooter.setSpeed(speed);
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
        shooter.turnOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        shooter.turnOff();
    }
}
