/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;

/**
 *
 * @author robot
 */
public class RunShooter extends CommandBase {
    //the speed we want the shooter to run at (must be between -1.0 and 1.0)
    private double requestedSpeed;
    private boolean display;
    
    public RunShooter() {
        //reserve the shooter
        requires(shooter);
        //shooter is initially not spinning
        requestedSpeed = 0.0;
        display=true;
        shooter.resetfrisbeefeeder();
        System.out.println("Starting with frisbee feeder reset");

    }

    // Called just before this Command runs the first time
    protected void initialize() {
        //the shooter is initially off
        
        SmartDashboard.putString("Shooter: ", "OFF");
         System.out.println(" Starting with Shooter Off");
   }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //b toggles the shooter on/off
        if (oi.isButtonPressed(OI.XBOX_BUTTON_B)) {
            if (shooter.isOn()) {
                shooter.turnOff();
                //write the state of the shooter to the Smart Dashboard
                SmartDashboard.putString("Shooter: ", "OFF");
                System.out.println("Shooter Off");
                requestedSpeed=0; //so shooter always starts from zero when turned on.
            }
            else {
                shooter.turnOn();
                //write the state of the shooter to the Smart Dashboard
                System.out.println("Shooter On");
                SmartDashboard.putString("Shooter: ", "ON");
            }
        }
        
        //the left/right bumpers decrement/increment the speed, respectively
        if (oi.isButtonPressed(OI.XBOX_BUTTON_LEFT_BUMPER) && requestedSpeed>0.0) 
        {
            requestedSpeed -= 0.1;
            System.out.println("Down requestedSpeed=" + Double.toString(requestedSpeed));
       }
        else if (oi.isButtonPressed(OI.XBOX_BUTTON_RIGHT_BUMPER) && requestedSpeed<1.0) 
        {
            requestedSpeed += 0.1;
             System.out.println("Up requestedSpeed=" + Double.toString(requestedSpeed));
       }
        if (requestedSpeed<0)  //We never want shooter to go backwards
        { 
            requestedSpeed=0;
        }
        
        //set the shooter to the requested speed
        shooter.setSpeed(requestedSpeed * -1.0); //fastest shooter speed is -1.0
        
     //Feed a frisbee into the shooter using A button
    
        if (oi.isButtonHeldDown(OI.XBOX_BUTTON_A) && shooter.isOn() ) {
                 shooter.activatefrisbeefeeder(); 
                 if(display==true)  
                 {
                     System.out.println("Feeding Frisbee");
                     display=false; //so only print it once
                 }
                   
            }
            else {
                shooter.resetfrisbeefeeder();
                display=true;
            }
        

        
        //write the data to SmartDashboard
        SmartDashboard.putNumber("Requested Speed", requestedSpeed);
        SmartDashboard.putNumber("Actual Speed", shooter.getSpeed());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
