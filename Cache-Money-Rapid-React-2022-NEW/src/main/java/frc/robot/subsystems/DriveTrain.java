package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveTrain extends Subsystem { 
    //I2C Port Setup  
    private static I2C.Port i2cport = I2C.Port.kOnboard;
    //Motor Variable setup
    private static CANSparkMax motorLeft1 = new CANSparkMax(RobotMap.MOTOR_LEFT_1_ID, MotorType.kBrushless);
    private static CANSparkMax motorLeft2 = new CANSparkMax(RobotMap.MOTOR_LEFT_2_ID, MotorType.kBrushless);
    private static CANSparkMax motorRight1 = new CANSparkMax(RobotMap.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
    private static CANSparkMax motorRight2 = new CANSparkMax(RobotMap.MOTOR_RIGHT_2_ID, MotorType.kBrushless);
    private static ColorSensorV3 colorsensor = new ColorSensorV3(i2cport);
    private static ColorMatch colormatcher = new ColorMatch();
    
    public static void setLeftMotors(double speed){
        motorLeft1.set(speed);
        motorLeft2.set(speed);
    }
    public static void setRightMotors(double speed){
        motorRight1.set(speed);
        motorRight2.set(speed);
    }
    public static void setRight2(double speed){
        motorRight2.set(speed);
    }
    public static void move(double left, double right){
        setLeftMotors(left);
        setRightMotors(right);
    }

    public static Color getColor(){
        return colorsensor.getColor();
    }  

    public static boolean colorMatchCheck(Color c){
        ColorMatchResult match = colormatcher.matchClosestColor(DriveTrain.getColor());
        if(match.color == c){
            return true;
        } else {
            return false;
        }

    }

    @Override
    protected void initDefaultCommand() {
        
    }
}
    