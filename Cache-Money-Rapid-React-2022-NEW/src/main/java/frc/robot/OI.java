package frc.robot;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.command.ShootBalls;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
        LeftBumpButton.whenPressed(new ShootBalls());
        RightBumpButton.whenPressed(new ShootBalls());
        
    }

    public double getDriverRawAxis(int axis){
        return xbox.getRawAxis(axis);
    }
    
    
}
