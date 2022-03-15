package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
public class Intake extends Subsystem {   
    //Motor Variable setup
  public static final int TILT_INTAKE_MOTOR= 0;
    private static TalonFX leftTilt= new TalonFX(RobotMap.LEFT_TILT_INTAKE_MOTOR);
    private static TalonFX rightTilt = new TalonFX(RobotMap.RIGHT_TILT_INTAKE_MOTOR);
    private static TalonFX go= new TalonFX(RobotMap.GO_INTAKE_MOTOR);
    private static CANSparkMax IndexMotor1 = new CANSparkMax(RobotMap.FrontIndexMotorLeft, MotorType.kBrushless);
    private static CANSparkMax IndexMotor2 = new CANSparkMax(RobotMap.FrontIndexMotorRight, MotorType.kBrushless);
    private static CANSparkMax IndexMotor3 = new CANSparkMax(RobotMap.BackIndexMotorLeft, MotorType.kBrushless);
    private static CANSparkMax IndexMotor4 = new CANSparkMax(RobotMap.BackIndexMotorRight, MotorType.kBrushless);
    
    public static void tilt(double speed){
        leftTilt.set(ControlMode.PercentOutput, -speed); 
        rightTilt.set(ControlMode.PercentOutput, speed);
 
    }
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
        System.out.println(IndexMotor3.getEncoder().getVelocity());
        System.out.println(IndexMotor4.getEncoder().getVelocity());
    }
    
    @Override
    protected void initDefaultCommand() {
        
    }
}
