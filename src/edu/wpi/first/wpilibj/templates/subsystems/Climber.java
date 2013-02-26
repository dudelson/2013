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
    //upper and lower lim switches
    private DigitalInput limUp, limDown;
    //is the climber on or off
    private boolean isOn;
    
    public Climber() {
        climberMotor = new Victor(RobotMap.climberVictor);
        limUp = new DigitalInput(RobotMap.climber_lim_up);
        limDown = new DigitalInput(RobotMap.climber_lim_down);
        isOn = false; 
    }
    
    protected void initDefaultCommand() {
        //this subsystem will look for input from the xbox by default
        //setDefaultCommand(new RunClimber());
    }
    
    //return the current speed of the motor
    public double getSpeed() {
        return climberMotor.get();
    }
    
    //set the speed of the motor
    //if the motor is supposed to be off, set the speed to 0
    public void setSpeed(double speed) {
        climberMotor.set(speed);
    }
    
    //move the climber into the "down" position
    public void moveDown() {
        setSpeed(-1.0);
    }
    
    //move the climber into the "up" (raised) position
    public void moveUp() {
        setSpeed(1.0);
    }
    
    public boolean isUpperLim() {
        return !limUp.get();
    }
    
    public boolean isLowerLim() {
        return !limDown.get();
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
        setSpeed(0.0);
    }
}
