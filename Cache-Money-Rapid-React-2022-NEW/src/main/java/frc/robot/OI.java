package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.command.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class OI {
     //Button inputs
    public static Joystick xbox = new Joystick(RobotMap.DRIVERCONTROLLER);
    public static Joystick joystick = new Joystick(RobotMap.BUTTONCONTROLLER);
    public static Button BlueButton = new JoystickButton(xbox,RobotMap.BUTTON_BLUE);
    public static Button RedButton = new JoystickButton(joystick,RobotMap.BUTTON_RED);
    public static Button GreenButton = new JoystickButton(joystick,RobotMap.BUTTON_GREEN);
    public static Button YellowButton = new JoystickButton(joystick,RobotMap.BUTTON_YELLOW);
    public static Button BlueJoystickButton = new JoystickButton(joystick, RobotMap.BUTTON_BLUE);
    public static Button LeftBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_LEFTBUMP);
    public static Button RightBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_RIGHTBUMP);
    public static Button BackButton = new JoystickButton(joystick,RobotMap.BUTTON_BACK);
    public static Button StartButton = new JoystickButton(joystick,RobotMap.BUTTON_START);
    public static Button joystickLeftBumpButton = new JoystickButton(joystick, RobotMap.BUTTON_LEFTBUMP);
    public static Button joystickRightBumpButton = new JoystickButton(joystick, RobotMap.BUTTON_RIGHTBUMP);
    public static Button joystickLeftStickButton = new JoystickButton(joystick, RobotMap.BUTTON_LEFTSTICK);
    public static Button joystickRightStickButton = new JoystickButton(joystick, RobotMap.BUTTON_RIGHTSTICK);


    public OI(){ 
        SmartDashboard.putString("oi constructor", "executed");
        YellowButton.whileHeld(new IndexBalls());
        //BLUE, GREEN, AND RED BUTTONS HAVE BEEN DECOMISSIONED


        RightBumpButton.whileHeld(new IntakeBalls());
        //LeftBumpButton.whileHeld(new IndexBalls());

        StartButton.whileHeld(new AutomatedClimb());
        BackButton.whileHeld(new AutomatedClimb());
        
        joystickLeftBumpButton.whileHeld(new IndexSingle());
        joystickRightBumpButton.whileHeld(new IndexSingle());

        joystickLeftStickButton.whenPressed(new SpeedAdjustment());
        joystickRightStickButton.whenPressed(new SpeedAdjustment());
    }

    public boolean bluePressed(){
        return BlueButton.get();
    }
    // blue used for tilt, yellow used for index backwards
    public boolean yellowPressed(){
        return YellowButton.get();
    }
    public boolean greenPressed(){
        return GreenButton.get();
    }
    public boolean redPressed(){
        return RedButton.get();
    }

    public boolean leftBumpPressed(){
        return LeftBumpButton.get();
    }

    public boolean leftStickPressed(){
        return joystickLeftStickButton.get();
    }

    public boolean startPressed(){
        return StartButton.get();
    }

    public boolean rightBumpPressed(){
        return joystickRightBumpButton.get();
    }

    public double getDriverRawAxisXbox(int axis){
        return xbox.getRawAxis(axis);
    }
    public double getDriverRawAxisJoystick(int axis){
        return joystick.getRawAxis(axis);
    }
    
    
    
    
}
