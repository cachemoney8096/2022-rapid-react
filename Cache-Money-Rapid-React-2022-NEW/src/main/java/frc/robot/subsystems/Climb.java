package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;

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

    public static void setExtensionMotionMagic(double targetPos, double kF){
        leftExtension.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
        rightExtension.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
    }

    public static void setPivotMotionMagic(double targetPos, double kF){
        leftPivot.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
        rightPivot.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
    }

    public static void configureExtensionPIDValues(double kP, double kI, double kD, double kF, double CMV, double MotionAcceleration, int SmoothingStrength){
        leftExtension.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        leftExtension.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        leftExtension.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        leftExtension.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        leftExtension.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        leftExtension.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        leftExtension.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing

        rightExtension.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        rightExtension.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        rightExtension.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        rightExtension.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        rightExtension.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        rightExtension.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        rightExtension.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
    }

    public static void configurePivotPIDValues(double kP, double kI, double kD, double kF, double CMV, double MotionAcceleration, int SmoothingStrength){
        leftPivot.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        leftPivot.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        leftPivot.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        leftPivot.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        leftPivot.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        leftExtension.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        leftExtension.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing

        rightExtension.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        rightExtension.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        rightExtension.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        rightExtension.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        rightExtension.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        rightExtension.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        rightExtension.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
    }



    @Override
    protected void initDefaultCommand() {

    }
}
    