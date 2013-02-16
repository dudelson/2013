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
public class Climber extends Subsystem {
    //the motor that spins the shooter
    private Victor climberMotor;
    //is the climber on or off
    private boolean isOn;
    //is the climber up or down (assuming it's on)
    private boolean isUp;
    
    public Climber() {
        climberMotor = new Victor(RobotMap.climberVictor);
        isOn = false; 
        isUp = false;
    }
    
    protected void initDefaultCommand() {
        //this subsystem will look for input from the xbox by default
        setDefaultCommand(new RunClimber());
    }
    
    //return the current speed of the motor
    public double getSpeed() {
        return climberMotor.get();
    }
    
    //set the speed of the motor
    //if the motor is supposed to be off, set the speed to 0
    public void setSpeed(double speed) {
        if (isOn) climberMotor.set(speed);
        else climberMotor.set(0.0);
    }
    
    //is the climber up (raised)
    public boolean isUp() {
        return isUp;
    }
    
    //move the climber into the "down" position
    public void moveDown() {
        setSpeed(-1.0);
        isUp = false;
    }
    
    //move the climber into the "up" (raised) position
    public void moveUp() {
        setSpeed(1.0);
        isUp = true;
    }
    
    //returns true if the motor is on
    public boolean isOn() {
        return isOn;
    }
    
    //turn the motor on
    public void turnOn() {
        isOn = true;
    }
    
    //turn the motor off
    public void turnOff() {
        isOn = false;
    }
}
