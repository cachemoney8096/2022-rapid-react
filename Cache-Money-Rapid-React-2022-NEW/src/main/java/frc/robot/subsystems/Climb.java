package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.BaseTalonPIDSetConfiguration;
 
import frc.robot.RobotMap;

public class Climb {   
    //Motor Variable setup
    private static TalonSRX leftExtension= new TalonSRX(RobotMap.LEFT_EXT_MOTOR);
    private static TalonSRX rightExtension= new TalonSRX(RobotMap.RIGHT_EXT_MOTOR);
    private static TalonSRX leftPivot = new TalonSRX(RobotMap.LEFT_PIVOT_MOTOR);
    private static TalonSRX rightPivot= new TalonSRX(RobotMap.RIGHT_PIVOT_MOTOR);

    //PID Integration Setup
    private static BaseTalonPIDSetConfiguration pidController = new BaseTalonPIDSetConfiguration(FeedbackDevice.QuadEncoder);
    
    public static void setExtensionMotors(double speed){
        leftExtension.set(ControlMode.PercentOutput, speed);
        rightExtension.set(ControlMode.PercentOutput, speed);   
    }
    public static void setPivotMotors(double speed){
        leftPivot.set(ControlMode.PercentOutput, speed);
        rightPivot.set(ControlMode.PercentOutput, speed);
    }

    public static void setRotatePivot(double angle){
        leftPivot.getActiveTrajectoryArbFeedFwd();
    }
};
    