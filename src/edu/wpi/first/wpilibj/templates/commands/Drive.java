/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;

/**
 *
 * @author David
 */
public class Drive extends CommandBase {
    private int driveMode = 1;
    
    public Drive() {
        requires(driveTrain);
    }
    
    public void initialize() {
    }
    
    public void execute() {
        System.out.println("Executing!");
        if (oi.isXDown()) driveMode = (driveMode+1) % 2; //if x is pressed
        switch (driveMode) {
            case 0: System.out.println("Drive mode: TANK");
                driveTrain.xboxTank(oi.getXboxYL() * 0.75, oi.getXboxYR() * 0.75);
                break;
            case 1: System.out.println("Drive mode: ARCADE");
                driveTrain.xboxArcade(oi.getXboxYL() * 0.75, oi.getXboxXL());
                break;
        }
    }
    
    public boolean isFinished() {
        return false;
    }
    
    public void end() {
        
    }
    
    public void interrupted() {
        
    }
}
