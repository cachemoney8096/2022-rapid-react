package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem{
    public static VictorSPX climbMotorExtend = new VictorSPX(0);
    public static VictorSPX climbMotorRetract = new VictorSPX(1);
    //public static JoystickButton
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }

    public static void extendLift(){
        // double speed = armDistance / deltaT;
        climbMotorExtend.set(ControlMode.PercentOutput, 1.0);
    }
    
    public static void retractLift(){
        climbMotorRetract.set(ControlMode.PercentOutput, -1.0);
    }  
    
    
    
    public static void stopLiftMotor(){
        climbMotorRetract.set(ControlMode.PercentOutput, 0.0);
    }

    
    
}

//documentation: http://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_victor_s_p_x.html