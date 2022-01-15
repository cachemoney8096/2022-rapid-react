package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Shooter {   
    //Motor Variable setup
    private static VictorSPX motor1= new VictorSPX(RobotMap.SHOOTER_MOTOR_1_ID);
    private static VictorSPX motor2= new VictorSPX(RobotMap.SHOOTER_MOTOR_2_ID);
    
    public static void setMotor1(double speed){
        motor1.set(ControlMode.PercentOutput, speed);
 
    }
    public static void setmotor2(double speed){
        motor2.set(ControlMode.PercentOutput, speed);
    }
    public static void shoot(double right){
        setmotor1(right);
        setmotor2(right);
    }
}