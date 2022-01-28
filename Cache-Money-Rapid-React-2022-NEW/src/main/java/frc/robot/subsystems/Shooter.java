package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends Subsystem {   
    //Motor Variable setup
    private static TalonSRX motor1= new TalonSRX(RobotMap.SHOOTER_MOTOR_1_ID);
    private static TalonSRX motor2= new TalonSRX(RobotMap.SHOOTER_MOTOR_2_ID);
    
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

        motor2.config_kP(0, kP); //TODO Understand what slotIdx means (first parameter)
        motor2.config_kI(0, kI); //TODO Understand what slotIdx means (first parameter)
        motor2.config_kD(0, kD); //TODO Understand what slotIdx means (first parameter)
        motor2.config_kF(0, kF); //TODO Understand what slotIdx means (first parameter)
        motor2.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        motor2.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        motor2.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
    }

    public static void setMotor1(double speed){
        motor1.set(ControlMode.PercentOutput, speed);
 
    }
    public static void setMotor2(double speed){
        motor2.set(ControlMode.PercentOutput, speed);
    }
    public static void shoot(double right){
        setMotor1(right);
        setMotor2(right);
        
    }
    @Override
    protected void initDefaultCommand() {
        
    }
}