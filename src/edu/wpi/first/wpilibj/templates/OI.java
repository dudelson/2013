
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private final int JOYSTICK_PORT = 1;
     
    //"Raw" values for every button/axis on the xbox controller
    public static int XBOX_BUTTON_A = 1;
    public static int XBOX_BUTTON_B = 2;
    public static int XBOX_BUTTON_X = 3;
    public static int XBOX_BUTTON_Y = 4;
    public static int XBOX_BUTTON_LEFT_BUMPER = 5;
    public static int XBOX_BUTTON_RIGHT_BUMPER = 6;
    public static int XBOX_BUTTON_BACK = 7;
    public static int XBOX_BUTTON_START = 8;
    public static int XBOX_BUTTON_LEFT_AXIS = 9;
    public static int XBOX_BUTTON_RIGHT_AXIS = 10;
    public static int XBOX_AXIS_LEFT_X = 1;
    public static int XBOX_AXIS_LEFT_Y = 2;
    public static int XBOX_AXIS_TRIGGER = 3;
    public static int XBOX_AXIS_RIGHT_X = 4;
    public static int XBOX_AXIS_RIGHT_Y = 5;
    
    //the state of a button is index XBOX_BUTTON - 1
    private boolean[] buttonStates = new boolean[10];
    
    //hopefully we don't switch to an actual joystick or this program is toast.
    private Joystick xbox; 
    
    public OI() {
        xbox = new Joystick(JOYSTICK_PORT);
    }
    
    //Convenience methods for getting each of the primary
    //axes on the xbox controller
    public double getXboxYL() {
        return xbox.getRawAxis(XBOX_AXIS_LEFT_Y);
    }
    
    public double getXboxYR() {
        return xbox.getRawAxis(XBOX_AXIS_RIGHT_Y);
    }
    
    public double getXboxXL() {
        return xbox.getRawAxis(XBOX_AXIS_LEFT_X);
    }
    
    public double getXboxXR() {
        return xbox.getRawAxis(XBOX_AXIS_RIGHT_X);
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
        boolean buttonDown = buttonStates[button - 1];
        if (xbox.getRawButton(button) && !buttonDown) {
            buttonStates[button - 1] = true;
            return true;
        }
        if (!xbox.getRawButton(button)) buttonStates[button - 1] = false;
        return false;
    } 
    
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

