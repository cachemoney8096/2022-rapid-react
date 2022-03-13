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
    public static Button RedButton = new JoystickButton(xbox,RobotMap.BUTTON_RED);
    public static Button GreenButton = new JoystickButton(xbox,RobotMap.BUTTON_GREEN);
    public static Button YellowButton = new JoystickButton(xbox,RobotMap.BUTTON_YELLOW);
    public static Button LeftBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_LEFTBUMP);
    public static Button RightBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_RIGHTBUMP);
    public static Button BackButton = new JoystickButton(xbox,RobotMap.BUTTON_BACK);
    public static Button StartButton = new JoystickButton(xbox,RobotMap.BUTTON_START);
   
    public OI(){
        SmartDashboard.putString("oi constructor", "executed");
        YellowButton.whileHeld(new IndexBalls());
        //BLUE BUTTON HAS BEEN DECOMMISSIONED
        RedButton.whenPressed(new Tilt(true));       
        GreenButton.whenPressed(new ShootBalls(false));

        RightBumpButton.whileHeld(new IntakeBalls());
        LeftBumpButton.whileHeld(new IndexBalls());

        StartButton.whenPressed(new AutomatedClimb(false));
        BackButton.whileHeld(new AutomatedClimb(true));
        
    }

    public boolean bluePressed(){
        return BlueButton.get();
    }

    public boolean yellowPressed(){
        return YellowButton.get();
    }

    public double getDriverRawAxis(int axis){
        return xbox.getRawAxis(axis);
    }
    
    
    
    
}
