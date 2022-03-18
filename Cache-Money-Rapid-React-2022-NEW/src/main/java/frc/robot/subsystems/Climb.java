package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.RobotMap;

public class Climb extends Subsystem {   
    //Motor Variable setup
    private static CANSparkMax leftExtension = new CANSparkMax(RobotMap.LEFT_EXT_MOTOR, MotorType.kBrushless);
    private static CANSparkMax rightExtension = new CANSparkMax(RobotMap.RIGHT_EXT_MOTOR, MotorType.kBrushless);
    private static TalonFX leftPivot = new TalonFX(RobotMap.LEFT_PIVOT_MOTOR);
    private static TalonFX rightPivot= new TalonFX(RobotMap.RIGHT_PIVOT_MOTOR);

    public static void setExtensionSpeed(double speed){
        leftExtension.set(-speed);
        rightExtension.set(speed);   
    }
    public static void setExtensionLeft(double speed){
        leftExtension.set(-speed);  
    }

    public static void setExtensionRight(double speed){
        rightExtension.set(speed);   
    }
    public static void setPivotMotorSpeed(double speed){
        leftPivot.set(ControlMode.PercentOutput, speed);
        rightPivot.set(ControlMode.PercentOutput, speed);
    }

    public static void setBrakeMode(){
        leftExtension.setIdleMode(IdleMode.kBrake);
        rightExtension.setIdleMode(IdleMode.kBrake);
        
    }

    public static void PIDExtend(double distanceIN, double kP, double kI, double kD){
        /*if(currentRotationsNeeded){
            currentRotations = DriveTrain.getPosition();
            currentRotationsNeeded = false;
        }*/
        leftExtension.getPIDController().setP(kP);
        leftExtension.getPIDController().setI(kI);
        leftExtension.getPIDController().setD(kD);
        leftExtension.getPIDController().setOutputRange(-0.5, 0.5);
        double rotations = distanceIN/Math.PI/RobotMap.EXT_WHEEL_DIAMETER*7;
        double reference = rotations*12.5/11;
        leftExtension.getPIDController().setReference(reference, CANSparkMax.ControlType.kPosition, 0);
        //rightExtension.follow(leftExtension, false);
        System.out.println("Encoder Value: " + leftExtension.getEncoder().getPosition());
    }

    public static void setExtensionPosition(double position){
        leftExtension.getEncoder().setPosition(0);
    }

    public static void setPivotMotorPosition(double distance){
        leftPivot.set(ControlMode.Position, distance);
        rightPivot.set(ControlMode.Position, distance);
    }

    public static void setPivotMotionMagic(double targetPos, double kF){
        leftPivot.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
        rightPivot.set(ControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, kF);
    }

    public static double getPivotPosition(){
        return rightPivot.getActiveTrajectoryPosition();
    }

    public static void configurePivotPIDValues(double kP, double kI, double kD, double kF, double CMV, double MotionAcceleration, int SmoothingStrength){
        leftPivot.config_kP(0, kP); 
        leftPivot.config_kI(0, kI); 
        leftPivot.config_kD(0, kD); 
        leftPivot.config_kF(0, kF); 
        leftPivot.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        leftPivot.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        leftPivot.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
        leftPivot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        rightPivot.config_kP(0, kP); 
        rightPivot.config_kI(0, kI); 
        rightPivot.config_kD(0, kD); 
        rightPivot.config_kF(0, kF); 
        rightPivot.configMotionCruiseVelocity(CMV); //It's in Sensor Units Per 100ms
        rightPivot.configMotionAcceleration(MotionAcceleration); //It's in Sensor Units Per 100ms
        rightPivot.configMotionSCurveStrength(SmoothingStrength); //0 for trapezoidal acceleration, higher values for more smoothing
        rightPivot.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }



    @Override
    protected void initDefaultCommand() {

    }
}
    