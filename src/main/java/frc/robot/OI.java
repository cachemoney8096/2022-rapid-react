package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.command.*;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class OI {
    /** Controller objects */
    public static Joystick driver = new Joystick(RobotMap.DRIVERCONTROLLER);
    public static Joystick operator = new Joystick(RobotMap.OPERATORCONTROLLER);

    /** Driver buttons */
    public static Button driverA = new JoystickButton(driver, RobotMap.BUTTON_GREEN);
    public static Button driverB = new JoystickButton(driver, RobotMap.BUTTON_RED);
    public static Button driverX = new JoystickButton(driver, RobotMap.BUTTON_BLUE);
    public static Button driverY = new JoystickButton(driver, RobotMap.BUTTON_YELLOW);
    public static Button driverRB = new JoystickButton(driver,RobotMap.BUTTON_RIGHTBUMP);

    /** Operator buttons */
    public static Button operatorA = new JoystickButton(operator,RobotMap.BUTTON_GREEN);
    public static Button operatorB = new JoystickButton(operator,RobotMap.BUTTON_RED);
    public static Button operatorX = new JoystickButton(operator, RobotMap.BUTTON_BLUE);
    public static Button operatorY = new JoystickButton(operator,RobotMap.BUTTON_YELLOW);
    public static Button operatorLB = new JoystickButton(operator, RobotMap.BUTTON_LEFTBUMP);
    public static Button operatorRB = new JoystickButton(operator, RobotMap.BUTTON_RIGHTBUMP);
    public static Button operatorLT = new JoystickButton(driver, RobotMap.BUTTON_LEFTSTICK);
    public static Button operatorRT = new JoystickButton(driver, RobotMap.BUTTON_RIGHTSTICK);
    public static Button operatorBackButton = new JoystickButton(operator,RobotMap.BUTTON_BACK);
    public static Button operatorStartButton = new JoystickButton(operator,RobotMap.BUTTON_START);

    public OI(){
        operatorB.whileHeld(new IndexBalls(false)); //Index out
        operatorRB.whileHeld(new IndexBalls(true)); //Index in

        driverY.whileHeld(new ShootBalls(true, true)); //Shoot
        driverY.whenReleased(new ShootBalls(true, false)); //Shoot stop

        operatorStartButton.whileHeld(new AutomatedClimb()); //Climb up
        operatorBackButton.whileHeld(new AutomatedClimb()); //Climb down

        operatorA.whileHeld(new Tilt(false)); //Intake down
        operatorX.whileHeld(new Tilt(true)); //Intake up

        driverRB.whileHeld(new IntakeBalls()); //Run intake
    }

    /** TODO: remove once tiltTrig is removed */
    public boolean bluePressed(){
        return operatorX.get();
    }

    public boolean greenPressed(){
        return operatorA.get();
    }

    public boolean redPressed(){
        return operatorB.get();
    }

    public boolean rightBumpPressed(){
        return driverRB.get();
    }

    public boolean startPressed(){
        return operatorStartButton.get();
    }

    public double getDriverRawAxis(int axis){
        return driver.getRawAxis(axis);
    }
    public double getOperatorRawAxis(int axis){
        return operator.getRawAxis(axis);
    }
}
