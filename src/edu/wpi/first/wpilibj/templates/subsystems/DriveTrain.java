/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 *
 * @author David
 */
public class DriveTrain extends Subsystem {
    private static final int LEFT_PORT = 1;
    private static final int RIGHT_PORT = 4;
    
    private Jaguar left;
    private Jaguar right;
    private RobotDrive drive;
    
    public DriveTrain() {
        left = new Jaguar(LEFT_PORT);
        right = new Jaguar(RIGHT_PORT);
        drive = new RobotDrive(left, right);
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
