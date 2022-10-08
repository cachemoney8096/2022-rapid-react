package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.DemandType;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;

/*
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
*/

public class Shooter extends Subsystem {   
    //Motor Variable setup
    private static TalonFX motor1= new TalonFX(RobotMap.SHOOTER_MOTOR_1_ID);
    private static TalonFX motor2= new TalonFX(RobotMap.SHOOTER_MOTOR_2_ID);
    public static double Kadjust = 0;
    
    
   


    /*public static void configureShooterPIDValues(double kP, double kI, double kD, double kF, double CMV, double MotionAcceleration, int SmoothingStrength){
        motor1.config_kP(0, kP); //SlotIndex 0 
        motor1.config_kI(0, kI); 
        motor1.config_kD(0, kD); 
        motor1.config_kF(0, kF); 
        motor1.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        motor1.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        motor1.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
        motor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);


        motor2.config_kP(0, kP);
        motor2.config_kI(0, kI);
        motor2.config_kD(0, kD); 
        motor2.config_kF(0, kF); 
        motor2.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        motor2.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        motor2.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
        motor2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }*/

    public static void SetPID(double kP, double kI, double kD, double kF, double iZone){
        motor1.config_kP(0, kP);
        motor1.config_kI(0, kI);
        motor1.config_kD(0, kD);
        motor1.config_kF(0, kF);
        motor1.config_IntegralZone(0, iZone);
        
        motor2.config_kP(0, kP);
        motor2.config_kI(0, kI);
        motor2.config_kD(0, kD);
        motor2.config_kF(0, kF);
        motor2.config_IntegralZone(0, iZone);
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
        motor1.set(ControlMode.Velocity, (RobotMap.SHOOTER_SETPOINT + Kadjust));
        motor2.set(ControlMode.Velocity, -(RobotMap.SHOOTER_SETPOINT + Kadjust));
        SmartDashboard.putString("ShootBraindead(speed)", "executed");
        // System.out.println(Kadjust);
    }

    public static double getTargetArea(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    }

    public static void speedAdjust(double adjust){
        // System.out.println("KADJUST PRE: " + Kadjust);
        Kadjust = adjust + Kadjust;
        // System.out.println("KADJUST POST: " + Kadjust);
    }

   /* public static void setMotionMagic(double targetPos, double kF){
        motor1.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
        motor2.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
    }*/

    public static void setVelocity(double targetVel, double kF){
        motor1.set(ControlMode.Velocity, targetVel, DemandType.ArbitraryFeedForward, kF);
        motor2.set(ControlMode.Velocity, targetVel, DemandType.ArbitraryFeedForward, kF);
        
    }

    @Override
    protected void initDefaultCommand() {
        
    }

}