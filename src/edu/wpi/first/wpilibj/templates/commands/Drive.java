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
    
    public Drive() {
        requires(driveTrain);
    }
    
    public void initialize() {
        
    }
    
    public void execute() {
        if (oi.isXDown()) {
            driveTrain.xboxArcade(oi.getXboxYL() * 0.75, oi.getXboxXL() * 0.75);
        } else if (!oi.isXDown()) {
            driveTrain.xboxTank(oi.getXboxYL() * 0.75, oi.getXboxYR() * 0.75);
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
