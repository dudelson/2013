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
public class FrisbeeShooter extends Subsystem {
    //the motor that spins the shooter
    private Victor shooterMotor;
    //the victor that adjusts the angle of the shooter
    private Victor angleVictor;
    //servo motors for the frisbee feeder
    private Servo feederServo1, feederServo2;
    //is the shooter on or off
    private boolean isOn;
    
    public FrisbeeShooter() {
        shooterMotor = new Victor(RobotMap.shooterVictor);
        angleVictor = new Victor(RobotMap.shooterAngleVictor);
        feederServo1 = new Servo(RobotMap.feederServo1);
        feederServo2 = new Servo(RobotMap.feederServo2);
    }
    
    protected void initDefaultCommand() {
        //this subsystem will look for input from the xbox by default
        setDefaultCommand(new RunShooter());
    }
    
    public void shoot(double angle, double speed) {
        //possibly implemented later
    }
    
    public void activateFrisbeeFeeder() {
        feederServo1.set(0.0);
        feederServo2.set(1.0);
    }
    
    public void resetFrisbeeFeeder() {
        feederServo1.set(1.0);
        feederServo2.set(0.0);
    }
    
    //return the current speed of the shooter motor
    public double getSpeed() {
        return shooterMotor.get();
    }
    
    //set the speed of the shooter motor
    //if the shooter is supposed to be off, set the speed to 0
    public void setSpeed(double speed) {
        shooterMotor.set(-speed);
    }
    
    //change the angle of the shooter
    public void changeAngle(double rate) {
        angleVictor.set(rate);
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
