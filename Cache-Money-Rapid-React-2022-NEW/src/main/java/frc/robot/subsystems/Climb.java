package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
 
import frc.robot.RobotMap;

public class Climb extends Subsystem {   
    //Motor Variable setup
    private static TalonSRX leftExtension= new TalonSRX(RobotMap.LEFT_EXT_MOTOR);
    private static TalonSRX rightExtension= new TalonSRX(RobotMap.RIGHT_EXT_MOTOR);
    private static TalonSRX leftPivot = new TalonSRX(RobotMap.LEFT_PIVOT_MOTOR);
    private static TalonSRX rightPivot= new TalonSRX(RobotMap.RIGHT_PIVOT_MOTOR);
    
    public static void setExtensionMotorSpeed(double speed){
        leftExtension.set(ControlMode.PercentOutput, speed);
        rightExtension.set(ControlMode.PercentOutput, speed);   
    }
    public static void setPivotMotorSpeed(double speed){
        leftPivot.set(ControlMode.PercentOutput, speed);
        rightPivot.set(ControlMode.PercentOutput, speed);
    }

    public static void setExtensionMotorPosition(double distance){
        leftExtension.set(ControlMode.Position, distance);
        rightExtension.set(ControlMode.Position, distance);   
    }

    public static void setPivotMotorPosition(double distance){
        leftPivot.set(ControlMode.Position, distance);
        rightPivot.set(ControlMode.Position, distance);
    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
        
    }
};
    