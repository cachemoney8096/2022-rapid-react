package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class DriveTrain {   
    //Motor Variable setup
    private static VictorSPX motorLeft1= new VictorSPX(RobotMap.MOTOR_LEFT_1_ID);
    private static VictorSPX motorLeft2= new VictorSPX(RobotMap.MOTOR_LEFT_1_ID);
    private static VictorSPX motorRight1= new VictorSPX(RobotMap.MOTOR_LEFT_1_ID);
    private static VictorSPX motorRight2= new VictorSPX(RobotMap.MOTOR_LEFT_1_ID);
    
    public static void setLeftMotors(double speed){
        motorLeft1.set(ControlMode.PercentOutput, speed);
        motorLeft2.set(ControlMode.PercentOutput, speed);   
    }
    public static void setRightMotors(double speed){
        motorRight1.set(ControlMode.PercentOutput, speed);
        motorRight2.set(ControlMode.PercentOutput, speed);
    }
    public static void move(double left, double right){
        setLeftMotors(left);
        setRightMotors(right);
    }
}
    