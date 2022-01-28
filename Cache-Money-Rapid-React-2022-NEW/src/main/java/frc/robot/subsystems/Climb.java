package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
 
import frc.robot.RobotMap;

public class Climb extends Subsystem {   
    //Motor Variable setup
    private static TalonFX leftExtension = new TalonFX(RobotMap.LEFT_EXT_MOTOR);
    private static TalonFX rightExtension = new TalonFX(RobotMap.RIGHT_EXT_MOTOR);
    private static TalonFX leftPivot = new TalonFX(RobotMap.LEFT_PIVOT_MOTOR);
    private static TalonFX rightPivot= new TalonFX(RobotMap.RIGHT_PIVOT_MOTOR);

    
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
        
    }
}
    