/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 *
 * @author David
 */
public class DriveTrain extends Subsystem {
    private Jaguar left, right;
    private RobotDrive drive;
    
    public DriveTrain() {
        left = new Jaguar(RobotMap.leftDriveMotor);
        right = new Jaguar(RobotMap.rightDriveMotor);
        drive = new RobotDrive(left, right);
        drive.setSafetyEnabled(false);
    }
    
    protected void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    
    public void turnLeft() {
        
    }
    
    public void turnRight() {
        
    }
    
    public void xboxArcade(double move, double rotate) {
        drive.arcadeDrive(move, rotate);
    }
    
    public void xboxTank(double left, double right) {
        drive.tankDrive(left, right);
    }
}
