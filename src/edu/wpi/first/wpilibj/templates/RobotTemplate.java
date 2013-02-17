/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    private Command autonomousProgram;
    private SendableChooser autonomousChooser;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Initialize all subsystems
        CommandBase.init();
        //init the smartdashboard autonomous program chooser
        autonomousChooser = new SendableChooser();
        autonomousChooser.addDefault("Shoot with 3 from left", new ShooterAutonomous(3, true));
        autonomousChooser.addObject("Shoot with 3 from right", new ShooterAutonomous(3, false));
        autonomousChooser.addObject("Shoot with 2 from left", new ShooterAutonomous(2, true));
        autonomousChooser.addObject("Shoot with 2 from right", new ShooterAutonomous(2, false));
        SmartDashboard.putData("Autonomous Program", autonomousChooser);
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousProgram = (Command) autonomousChooser.getSelected();
        autonomousProgram.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousProgram.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
