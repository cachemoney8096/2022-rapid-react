package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.Robot;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class Intake extends Subsystem {   
    //Motor Variable setup
 
    private static TalonFX leftTilt= new TalonFX(RobotMap.LEFT_TILT_INTAKE_MOTOR);
    //private static TalonFX rightTilt = new TalonFX(RobotMap.RIGHT_TILT_INTAKE_MOTOR);
    private static TalonFX go= new TalonFX(RobotMap.GO_INTAKE_MOTOR);
    private static CANSparkMax IndexMotor1 = new CANSparkMax(RobotMap.FrontIndexMotorLeft, MotorType.kBrushless);
    private static CANSparkMax IndexMotor2 = new CANSparkMax(RobotMap.FrontIndexMotorRight, MotorType.kBrushless);
    private static CANSparkMax IndexMotor3 = new CANSparkMax(RobotMap.BackIndexMotorLeft, MotorType.kBrushless);
    private static CANSparkMax IndexMotor4 = new CANSparkMax(RobotMap.BackIndexMotorRight, MotorType.kBrushless);

    private static boolean limitDisabled = false;
    private static Servo lockServo = new Servo(2);
    public static double Kadjust1 = 0;

    public static void tilt(double speed){
        leftTilt.set(ControlMode.PercentOutput, -(speed)); 
        //leftTilt.set(ControlMode.PercentOutput, 0); 
        //rightTilt.set(ControlMode.PercentOutput, speed); 
    }

    public static void resetKadjust(){
        Kadjust1 = 0;
    }
    public static void speedAdjust(double adjust){
        // System.out.println("KADJUST PRE: " + Kadjust1);
        Kadjust1 = adjust + Kadjust1;
        // System.out.println("KADJUST POST: " + Kadjust1);
    }
    public static void setTiltBrakeMode(){
        leftTilt.setNeutralMode(NeutralMode.Brake);
    }
    public static void setIndexerBrakeMode(){
        IndexMotor3.setIdleMode(IdleMode.kBrake);
        IndexMotor4.setIdleMode(IdleMode.kBrake);
    }

    /*public static void tiltPID(double position, double kP, double kD, double kF){
        leftTilt.config_kP(0, kP);
        leftTilt.config_kD(0, kD);
        leftTilt.config_kF(0, kF);
        leftTilt.set(ControlMode.Position, position);
    }*/
    public static void go(double speed){
        go.set(ControlMode.PercentOutput, speed);
    }
    public static void Limit(){
        IndexMotor1.setSmartCurrentLimit(20);
        IndexMotor2.setSmartCurrentLimit(20);
        IndexMotor3.setSmartCurrentLimit(20);
        IndexMotor4.setSmartCurrentLimit(20);
    }
    public static void FrontIndex(double speed){
        IndexMotor1.set(-speed);
        IndexMotor2.set(speed);
    }
    public static void BackIndex(double speed){
        IndexMotor3.set(-speed);
        IndexMotor4.set(speed);
        // System.out.println(IndexMotor3.getEncoder().getVelocity());
        // System.out.println(IndexMotor4.getEncoder().getVelocity());
    }
    
    public static void setLockAngle(double degrees){
        lockServo.setAngle(degrees);
    }

    public static double getLockAngle(){
        return lockServo.getAngle();
    }

    public static void setLimit(){
        // leftTilt.configForwardSoftLimitThreshold(-5000);
        // leftTilt.configReverseSoftLimitThreshold(-24000);//TILT DOWN
        // leftTilt.configForwardSoftLimitEnable(true);
        // leftTilt.configReverseSoftLimitEnable(true);
        // System.out.println("LIMITING TILT");
    }

    public static void unsetLimit(){
        //leftTilt.setSelectedSensorPosition(0);
        // leftTilt.configForwardSoftLimitEnable(false);
        // leftTilt.configReverseSoftLimitEnable(false);
        // System.out.println("UNLIMITING TILT");
    } 

    public static void resetLimit(){
        // Intake.unsetLimit();
        // leftTilt.setSelectedSensorPosition(0);
        // Intake.setLimit();

    }

    public static void enableLimit(){
        // if(Robot.m_oi.XboxBluePressed()){
        //     leftTilt.setSelectedSensorPosition(0);
        //     Intake.setLimit();
        //     limitDisabled = false;
        // }

    }

    public static void disableLimit(){
        // if(Robot.m_oi.XboxYellowPressed()){
        //     Intake.unsetLimit();
        //     limitDisabled = true;
        // }
    }

    public static void printLimitStatus(){
        if(limitDisabled){
            // System.out.println("LIMIT DISABLED");
        } else {
            // System.out.println("LIMIT ENABLED");
        }
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}
