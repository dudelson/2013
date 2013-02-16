/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author David
 */

public class Team1512Joystick extends Joystick {
    //"Raw" values for every button/axis on the xbox controller
    public static final int XBOX_BUTTON_A = 1;
    public static final int XBOX_BUTTON_B = 2;
    public static final int XBOX_BUTTON_X = 3;
    public static final int XBOX_BUTTON_Y = 4;
    public static final int XBOX_BUTTON_LEFT_BUMPER = 5;
    public static final int XBOX_BUTTON_RIGHT_BUMPER = 6;
    public static final int XBOX_BUTTON_BACK = 7;
    public static final int XBOX_BUTTON_START = 8;
    public static final int XBOX_BUTTON_LEFT_AXIS = 9;
    public static final int XBOX_BUTTON_RIGHT_AXIS = 10;
    public static final int XBOX_AXIS_LEFT_X = 1;
    public static final int XBOX_AXIS_LEFT_Y = 2;
    public static final int XBOX_AXIS_TRIGGER = 3;
    public static final int XBOX_AXIS_RIGHT_X = 4;
    public static final int XBOX_AXIS_RIGHT_Y = 5;
    
    //the state of a button is index XBOX_BUTTON - 1
    private boolean[] buttonStates = new boolean[10];
    
    public Team1512Joystick(int port) {
        super(port);
    }
    
    //Convenience methods for getting each of the primary
    //axes on the xbox controller
    public double getYL() {
        return getRawAxis(XBOX_AXIS_LEFT_Y);
    }
    
    public double getYR() {
        return getRawAxis(XBOX_AXIS_RIGHT_Y);
    }
    
    public double getXL() {
        return getRawAxis(XBOX_AXIS_LEFT_X);
    }
    
    public double getXR() {
        return getRawAxis(XBOX_AXIS_RIGHT_X);
    }
    //@Nate 
    //adding some triggers for arcade drive
    
    public boolean getLTRG() {
        //if the left trigger is pressed the analog value will be false
        if (getRawAxis(XBOX_AXIS_TRIGGER) < 0.0) return true;
        else return false;
    }
    
    public boolean getRTRG() {
        //likewise when the right trigger is pressed the analog value must be true
        if (getRawAxis(XBOX_AXIS_TRIGGER) > 0.0) return true;
        else return false;
    }
    
    /* This method returns true ONLY ONCE when a button is
     * pressed/held down. That is, when the user initially
     * presses the button, a value of true will be returned, 
     * however in subsequent calls to this method, if the
     * button has still not been released, the method will 
     * return false. This method preserves the state of each
     * button (pressed/not pressed) between method calls by 
     * using the array buttonStates to hold button states.
     * The method cannot return true while the button is in
     * a "pressed" state. The state of a button changes when 
     * the button is released.
     */
    public boolean isButtonPressed(int button) {
        if (getRawButton(button) && !buttonStates[button - 1]) {
            buttonStates[button - 1] = true;
            return true;
        }
        if (!getRawButton(button)) {
            buttonStates[button - 1] = false;
        }
        return false;
    } 
}
