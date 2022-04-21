package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.command.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class OI {
     //Joystick inputs
    public static Joystick xbox = new Joystick(RobotMap.DRIVERCONTROLLER);
    public static Joystick joystick = new Joystick(RobotMap.BUTTONCONTROLLER);

    //Driver Buttons
    public static Button rightBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_RIGHTBUMP);
    public static Button XBoxGreenButton = new JoystickButton(xbox, RobotMap.BUTTON_GREEN);
    public static Button XBoxRedButton = new JoystickButton(xbox, RobotMap.BUTTON_RED);
    public static Button XBoxBlueButton = new JoystickButton(xbox, RobotMap.BUTTON_BLUE);
    public static Button XboxYellowButton = new JoystickButton(xbox, RobotMap.BUTTON_YELLOW);
    //Joystick Buttons
    public static Button RedButton = new JoystickButton(joystick,RobotMap.BUTTON_RED); //CLIMB SINGLE RIGHT
    public static Button GreenButton = new JoystickButton(joystick,RobotMap.BUTTON_GREEN); //CLIMB SINGLE LEFT
    public static Button YellowButton = new JoystickButton(joystick,RobotMap.BUTTON_YELLOW); //INDEX OUT
    public static Button BlueJoystickButton = new JoystickButton(joystick, RobotMap.BUTTON_BLUE); //TILT UP
    public static Button BackButton = new JoystickButton(joystick,RobotMap.BUTTON_BACK); //CLIMB DOWN
    public static Button StartButton = new JoystickButton(joystick,RobotMap.BUTTON_START); //CLIMB UP
    public static Button joystickLeftBumpButton = new JoystickButton(joystick, RobotMap.BUTTON_LEFTBUMP); //INDEX BALLS
    public static Button joystickRightBumpButton = new JoystickButton(joystick, RobotMap.BUTTON_RIGHTBUMP); //INTAKE BALLS
    public static Button joystickLeftStickButton = new JoystickButton(xbox, RobotMap.BUTTON_LEFTSTICK); //
    public static Button joystickRightStickButton = new JoystickButton(xbox, RobotMap.BUTTON_RIGHTSTICK);
    

    public OI(){ 
        SmartDashboard.putString("oi constructor", "executed");
        //XboxBlueButton.whenPressed(new IntakeLimit(false));
        //XboxYellowButton.whenPressed(new IntakeLimit(true));
        XBoxGreenButton.whenPressed(new IntakeUnlock());
        XBoxRedButton.whenPressed(new IntakeLock());
        YellowButton.whileHeld(new IndexBalls());
        //BLUE, GREEN, AND RED BUTTONS HAVE BEEN DECOMISSIONED


        joystickRightBumpButton.whileHeld(new IntakeBalls());//previously intake
        joystickLeftBumpButton.whileHeld(new IndexBalls());

        StartButton.whileHeld(new AutomatedClimb());
        BackButton.whileHeld(new AutomatedClimb());
        
        //joystickLeftBumpButton.whileHeld(new IndexSingle());
        //joystickRightBumpButton.whileHeld(new IndexSingle());

        //joystickLeftStickButton.whenPressed(new SpeedAdjustment());
        //joystickRightStickButton.whenPressed(new SpeedAdjustmentIntake());
    }

    public boolean XboxBluePressed(){
        return XBoxBlueButton.get();
    }

    public boolean bluePressed(){
        return BlueJoystickButton.get();
    }
    // blue used for tilt, yellow used for index backwards
    public boolean yellowPressed(){
        return YellowButton.get();
    }

    public boolean XboxYellowPressed(){
        return XboxYellowButton.get();
    }

    public boolean greenPressed(){
        return GreenButton.get();
    }
    public boolean redPressed(){
        return RedButton.get();
    }

    public boolean rightBumpPressed(){
        return rightBumpButton.get();
    }

    public boolean leftStickPressed(){
        return joystickLeftStickButton.get();
    }

    public boolean startPressed(){
        return StartButton.get();
    }

  /*  public boolean rightBumpPressed(){
        return joystickRightBumpButton.get();
    }*/

    public double getDriverRawAxisXbox(int axis){
        return xbox.getRawAxis(axis);
    }
    public double getDriverRawAxisJoystick(int axis){
        return joystick.getRawAxis(axis);
    }
    
    
    
    
}
