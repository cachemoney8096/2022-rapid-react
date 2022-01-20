package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;

public class Climb {   
    //Motor Variable setup
    private static TalonSRX leftExtension= new TalonSRX(RobotMap.LEFT_EXT_MOTOR);
    private static TalonSRX rightExtension= new TalonSRX(RobotMap.RIGHT_EXT_MOTOR);
    private static TalonSRX leftPivot = new TalonSRX(RobotMap.LEFT_PIVOT_MOTOR);
    private static TalonSRX rightPivot= new TalonSRX(RobotMap.RIGHT_PIVOT_MOTOR);
    
    public static void setExtensionMotors(double speed){
        leftExtension.set(ControlMode.PercentOutput, speed);
        rightExtension.set(ControlMode.PercentOutput, speed);   
    }
    public static void setPivotMotors(double speed){
        leftPivot.set(ControlMode.PercentOutput, speed);
        rightPivot.set(ControlMode.PercentOutput, speed);
    }
}
    