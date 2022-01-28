package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem {   
    //Motor Variable setup
  public static final int TILT_INTAKE_MOTOR= 0;
    private static TalonSRX tilt= new TalonSRX(RobotMap.TILT_INTAKE_MOTOR);
    private static TalonSRX go= new TalonSRX(RobotMap.GO_INTAKE_MOTOR);
    
    public static void tilt(double speed){
        tilt.set(ControlMode.PercentOutput, speed);
 
    }
    public static void go(double speed){
        go.set(ControlMode.PercentOutput, speed);
    }
    
    @Override
    protected void initDefaultCommand() {
        
    }
}
