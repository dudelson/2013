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
public class Climber extends Subsystem {
    //the motor that spins the shooter
    private Victor climberMotor;
    //is the shooter on or off
    private boolean isOn;
    
    public Climber() {
        climberMotor = new Victor(6);
    }
    
    protected void initDefaultCommand() {
        //this subsystem will look for input from the xbox by default
        setDefaultCommand(new RunClimber());
    }
    
    public void shoot(double angle, double speed) {
        //possibly implemented later
    }
    
    //return the current speed of the shooter motor
    public double getSpeed() {
        return climberMotor.get();
    }
    
    //set the speed of the shooter motor
    //if the shooter is supposed to be off, set the speed to 0
    public void setSpeed(double speed) {
        if (isOn) climberMotor.set(speed);
        else climberMotor.set(0.0);
    }
    
    //returns true if the shooter is on
    //this DOES NOT MEAN the shooter must be spinning
    //it is possible for the shooter to be "on" with a value of 0.0
    public boolean isOn() {
        return isOn;
    }
    
    //turn the shooter on
    public void turnOn() {
        isOn = true;
    }
    
    //turn the shooter off
    public void turnOff() {
        isOn = false;
    }
}
