/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author robot
 */
public class FrisbeeFeeder extends Subsystem {
    //the relay that controls the feeder
    Relay feeder1;
    /* The feeder requires two limit switches
     * Stop: The feeder starts touching this switch and knows to stop when it touches it again.
     * Bump: The feeder "bumps" this switch and knows to change direction.
     */
    DigitalInput limStop, limBump;
    
    public FrisbeeFeeder() {
        feeder1 = new Relay(RobotMap.feederRelay);
        limStop = new DigitalInput(RobotMap.feeder_lim_stop);
        limBump = new DigitalInput(RobotMap.feeder_lim_bump);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //Methods to explicitly set the state of the feeder relay
    public void turnOff() {
        feeder1.set(Relay.Value.kOff);
    }
    
    public void setForward() {
        feeder1.set(Relay.Value.kForward);
    }
    
    public void setBackward() {
        feeder1.set(Relay.Value.kReverse);
    }
    
    //Convenience methods to get the state of the lim switches separately
    public boolean getLimStop() {
        return !limStop.get();
    }
    
    public boolean getLimBump() {
        return !limBump.get();
    }
    
    //get the state of the feeder as a string
    public String getState() {
        return feeder1.get().toString();
    }
    
}
