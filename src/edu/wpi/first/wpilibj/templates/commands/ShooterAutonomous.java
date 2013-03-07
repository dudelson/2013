/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author robot
 */

/*
 * DRIVING:
 * 1s @ .5 power => 3' 4"
 * 
 * TURNING:
 * 1s @ .5 power => 30deg
 * 
 * 12v => 83% power 
 */
 /* This is the default autonomous command, to be used when the robot
  * is starting partially outside of the autozone and is allowed 3 disks.
  */
public class ShooterAutonomous extends CommandBase {
    //all possible positions for the autonomous program
    public static final int POSITION_LEFT_BACK   = 0;
    public static final int POSITION_RIGHT_BACK  = 1;
    public static final int POSITION_LEFT_FRONT  = 2;
    public static final int POSITION_RIGHT_FRONT = 3;
    public static final int POSITION_CENTER_BACK = 4;
   /* The power constant scales with the battery voltage to provide more accurate
    * shots at lower voltages. It it obtained by selecting the relationship between
    * output power and full voltage:
    *      PK / v = P_out
    * With a full battery (v = 12.0) and a selected benchmark power output (say .75):
    *      PK / 12.0 = .75
    *      PK = .75 * 12.0 = 9.0
    * So in this case the power constant becomes 9.0. Full power will be reached 
    * at 9 volts.
    */
    private static final double POWER_CONSTANT = 10.0; 

    //the position for this autonomous command
    private int position;
    //passed to isFinished() as true once we're done
    private boolean isFinished;
    
    private int feederCount;
    
    public ShooterAutonomous() {
        this(POSITION_CENTER_BACK);
    }
    
    public ShooterAutonomous(int position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
        requires(shooter);
        requires(feeder);
        
        this.position = position;
        feederCount = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        isFinished = false;
        
        switch(this.position) {
            case POSITION_LEFT_BACK:
                /* Using 0.8 as a shooter speed worked pretty well.
                 * We even scored a few autonomously :)
                 */
                shooter.setSpeed(0.8);
                /*
                driveTrain.driveStraight(0.5);
                Timer.delay(0.5);
                driveTrain.turnRight(0.5);
                Timer.delay(1.0);
                driveTrain.stop();
                */
            case POSITION_RIGHT_BACK:
                shooter.setSpeed(0.8);
                /*
                driveTrain.driveStraight(0.5);
                Timer.delay(0.5);
                driveTrain.turnLeft(0.5);
                Timer.delay(1.0);
                driveTrain.stop();
                */
            case POSITION_LEFT_FRONT:
                shooter.setSpeed(0.8);
            case POSITION_RIGHT_FRONT:
                shooter.setSpeed(0.8);
            case POSITION_CENTER_BACK:
                shooter.setSpeed(0.8);
        }    
        Timer.delay(6.0); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {  
        if (feeder.getLimStop()) {
            feeder.turnOff();
            Timer.delay(1.0);
            if (feederCount <= 2) {
                feeder.setForward();
                feederCount++;
                Timer.delay(0.1);
            } else isFinished = true;
        //bump
        } else if (feeder.getLimBump()) {
            feeder.turnOff();
            Timer.delay(0.1);
            feeder.setBackward();
        } 
    }
        
        //activate the shooter first so that it's up to speed
        //by the time we need to shoot
        /*
        shooter.turnOn();
        double power = POWER_CONSTANT / DriverStation.getInstance().getBatteryVoltage();
        shooter.setSpeed(.5);
        SmartDashboard.putNumber("Requested shooter speed (auto): ", power);
        Timer.delay(6);
        */
        //drive to the shooting position
        /*
        driveTrain.driveStraight(0.5);
        Timer.delay(1.3);
        driveTrain.stop();
        Timer.delay(1.5);
        if (isFromLeft) {
            driveTrain.turnRight(0.5);
        } else {
            driveTrain.turnLeft(0.5);
        }     
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(2.0);
        */
        //FIRE!
        /*
        for(int i=0; i<numShots; i++) {
            //update the shooter speed each loop
            power = POWER_CONSTANT / DriverStation.getInstance().getBatteryVoltage();
            shooter.setSpeed(.4);
            SmartDashboard.putNumber("Requested shooter speed (auto): ", power);
            SmartDashboard.putNumber("numshots: ", 0);
            //f;askdjf;lkdsaj;lkjdsajlkjfds
            //stop
            feeder.setBackward();
            Timer.delay(0.1);
            if (feeder.getLimStop()) {
                feeder.turnOff();
                isFinished = true;
            //bump
            } else if (feeder.getLimBump()) {
                feeder.turnOff();
                Timer.delay(0.1);
                feeder.setForward();
            }
        }
        //end the command
        Timer.delay(5);
        shooter.turnOff();
        isFinished = true;
        
        /* drive in a square test code
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.driveStraight(0.5);
        Timer.delay(2.0);
        driveTrain.stop();
        Timer.delay(0.2);
        driveTrain.turnLeft(0.5);
        Timer.delay(0.95);
        driveTrain.stop();
        Timer.delay(0.2);
        isFinished = true;
        */


    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
//        System.out.println("[Auto] Finished!");
        while (!feeder.getLimStop()) {
            feeder.setBackward();
        }
        feeder.turnOff();
        shooter.turnOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//        System.out.println("auto canceled!");
        while (!feeder.getLimStop()) {
            feeder.setBackward();
        }
        feeder.turnOff();
        shooter.turnOff();
//        shooter.turnOff();
    }
}
