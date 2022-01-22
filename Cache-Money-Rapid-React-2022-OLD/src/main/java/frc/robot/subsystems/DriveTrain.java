package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import com.revrobotics.CANSparkMax;

public class DriveTrain {   
    //Motor Variable setup
    private static CANSparkMax motorLeft1 = new CANSparkMax(RobotMap.MOTOR_LEFT_1_ID);
    private static CANSparkMax motorLeft2 = new CANSparkMax(RobotMap.MOTOR_LEFT_2_ID);
    private static CANSparkMax motorRight1 = new CANSparkMax(RobotMap.MOTOR_RIGHT_1_ID);
    private static CANSparkMax motorRight2 = new CANSparkMax(RobotMap.MOTOR_RIGHT_2_ID);
    
    public static void setLeftMotors(double speed){
        motorLeft1.set(speed);
        motorLeft2.set(speed);
    }
    public static void setRightMotors(double speed){
        motorRight1.set(speed);
        motorRight2.set(speed);
    }
    public static void setRight2(double speed){
        motorRight2.set(speed);
    }
    public static void move(double left, double right){
        setLeftMotors(left);
        setRightMotors(right);
    }
}
    