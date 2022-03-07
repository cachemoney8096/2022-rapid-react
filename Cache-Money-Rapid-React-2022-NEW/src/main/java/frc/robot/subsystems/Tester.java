package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Tester extends Subsystem { 
    //Motor Variable setup
    private static CANSparkMax testMotor = new CANSparkMax(RobotMap.MOTOR_TEST_ID, MotorType.kBrushless);

    private static double initEncoderPos = 0.0;

    public static void setTestMotor(double speed){
        testMotor.set(speed);
    }

    public static void setReference(double rotations){
        testMotor.getPIDController().setP(0.000001);
        testMotor.getPIDController().setReference(rotations, ControlType.kPosition, 0);
    }

    public static void printPosition(){
        if(initEncoderPos == 0){
            initEncoderPos = testMotor.getEncoder().getPosition();
        }
        System.out.println(testMotor.getEncoder().getPosition() - initEncoderPos);
    }

    @Override
    protected void initDefaultCommand() {
        
    }

}
    