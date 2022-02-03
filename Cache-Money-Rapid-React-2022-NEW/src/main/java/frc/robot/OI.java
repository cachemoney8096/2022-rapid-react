package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.command.*;
import frc.robot.subsystems.*;
public class OI {
     //Button inputs
    public static Joystick xbox = new Joystick(RobotMap.DRIVERCONTROLLER);
    public static Joystick joystick = new Joystick(RobotMap.BUTTONCONTROLLER);
    public static Button BlueButton = new JoystickButton(xbox,RobotMap.BUTTON_BLUE);
    public static Button RedButton = new JoystickButton(xbox,RobotMap.BUTTON_RED);
    public static Button GreenButton = new JoystickButton(xbox, RobotMap.BUTTON_GREEN);
    public static Button YellowButton = new JoystickButton(xbox,RobotMap.BUTTON_YELLOW);
    public static Button LeftBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_LEFTBUMP);
    public static Button RightBumpButton = new JoystickButton(xbox,RobotMap.BUTTON_RIGHTBUMP);
    public static Button BackButton = new JoystickButton(xbox,RobotMap.BUTTON_BACK);
    public static Button StartButton = new JoystickButton(xbox,RobotMap.BUTTON_START);
    public static Button leftTrigger = new JoystickButton(xbox, RobotMap.BUTTON_LEFTTRIG);
    public static Button rightTrigger = new JoystickButton(xbox, RobotMap.BUTTON_RIGHTTRIG);

    public OI(){
        rightTrigger.whenPressed(new ShootBalls());
        RedButton.whenPressed(new AutomatedClimb());
        RightBumpButton.whenPressed(new IntakeCommands());
        
    }

    public double getDriverRawAxis(int axis){
        return xbox.getRawAxis(axis);
    }
    
    
}
