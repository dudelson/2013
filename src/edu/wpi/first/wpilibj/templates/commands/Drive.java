/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;

/**
 *
 * @author David
 */
public class Drive extends CommandBase {
    //the current drive mode
    private int driveMode;
    
    //these aren't completely necessary, but
    //are mainly to avoid confusion as to which
    //integer corresponds to which drive mode
    public static final int DRIVE_MODE_TANK = 0;
    public static final int DRIVE_MODE_ARCADE = 1;
    
    public Drive() {
        //reserve the drive train
        requires(driveTrain);
        //robot is initially using tank drive
        driveMode = DRIVE_MODE_TANK;
    }
    
    public void initialize() {
        
    }
    
    public void execute() {
        // x button toggles drive mode
        if (oi.isButtonPressed(OI.XBOX_BUTTON_X)) {
            driveMode = (driveMode+1) % 2;
        }
        
        //switch statement for style points
        //if the current drive mode is tank, use the left and right y axes to drive
        //if the current drive mode is arcade, use only the left stick to drive
        switch (driveMode) {
            case DRIVE_MODE_TANK:
                driveTrain.xboxTank(oi.getXboxYL() * 0.75, oi.getXboxYR() * 0.75);
                break;
            case DRIVE_MODE_ARCADE:
                driveTrain.xboxArcade(oi.getXboxYL() * 0.75, oi.getXboxXL());
                break;
        }
        //print the current drive mode to the SmartDashboard
        SmartDashboard.putString("Drive Mode: ", getDriveMode());
    }
    
    //switch statement for style points
    //returns a string based on the current drive mode
    //if there is no current drive mode, return null
    public String getDriveMode() {
        switch (driveMode) {
            case DRIVE_MODE_TANK:
                return "TANK";
            case DRIVE_MODE_ARCADE:
                return "ARCADE";
            default:
                return null;
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
