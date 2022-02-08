package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Shooter extends Subsystem {   
    //Motor Variable setup
    private static TalonFX motor1= new TalonFX(RobotMap.SHOOTER_MOTOR_1_ID);
    private static TalonFX motor2= new TalonFX(RobotMap.SHOOTER_MOTOR_2_ID);
    
    public static void SmartDashBoard(){
    SmartDashboard.putNumber("kP", 0.00015);//TODO Figure out how to use this
    SmartDashboard.putNumber("kI", 0.0000001);
    SmartDashboard.putNumber("kD", 0);
    SmartDashboard.putNumber("kF", 0.00018);
    SmartDashboard.putNumber("IZone", 250);
    SmartDashboard.putNumber("IMax", 0);
    SmartDashboard.putNumber("Command Speed", 6800);
    }


    public static void configureShooterPIDValues(double kP, double kI, double kD, double kF, double CMV, double MotionAcceleration, int SmoothingStrength){
        motor1.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        motor1.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        motor1.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        motor1.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        motor1.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        motor1.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        motor1.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
        motor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);


        motor2.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        motor2.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        motor2.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        motor2.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        motor2.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        motor2.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        motor2.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
        motor2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public static double getHorizontalOffsetAngle(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    }

    public static double getVerticalOffsetAngle(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    }

    public static boolean checkValidTargets(){
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0) == 1){
            return true;
        }
        return false;
    }
    public static void ShootBraindead(double speed){
        motor1.set(ControlMode.PercentOutput, -speed);
        motor2.set(ControlMode.PercentOutput, speed);
        SmartDashboard.putString("ShootBraindead(speed)", "executed");
    }

    public static double getTargetArea(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    }

    public static void setMotionMagic(double targetPos, double kF){
        motor1.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
        motor2.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
    }

    public static void setVelocity(double targetVel, double kF){
        motor1.set(ControlMode.Velocity, targetVel, DemandType.ArbitraryFeedForward, kF);
        motor2.set(ControlMode.Velocity, targetVel, DemandType.ArbitraryFeedForward, kF);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}