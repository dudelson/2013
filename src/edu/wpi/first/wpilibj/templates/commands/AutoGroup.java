/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.*;

/**
 *
 * @author robot
 */
/* 
 * THIS CLASS NO LONGER USED BECAUSE I DON'T KNOW HOW TO USE COMMAND GROUPS.
 */
public class AutoGroup extends CommandGroup {
    
    public AutoGroup(int position) {
        
        addSequential(new ShooterAutonomous(position));
        addParallel(new WaitCommand(7.0));
        addSequential(new RunFeeder());
        addSequential(new WaitCommand(0.5));
        addSequential(new RunFeeder());
        addSequential(new WaitCommand(0.5));
        addSequential(new RunFeeder());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
