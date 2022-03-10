package frc.robot.subsystems;


import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort.Port;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;
/*
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
*/

public class DriveTrain extends Subsystem { 
    //I2C Port Setup  
    private static I2C.Port i2cport = I2C.Port.kOnboard;
    //Motor Variable setup
    private static CANSparkMax motorLeft1 = new CANSparkMax(RobotMap.MOTOR_LEFT_1_ID, MotorType.kBrushless);
    private static CANSparkMax motorLeft2 = new CANSparkMax(RobotMap.MOTOR_LEFT_2_ID, MotorType.kBrushless);
    private static CANSparkMax motorRight1 = new CANSparkMax(RobotMap.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
    private static CANSparkMax motorRight2 = new CANSparkMax(RobotMap.MOTOR_RIGHT_2_ID, MotorType.kBrushless);
  //  private static ColorSensorV3 colorsensor = new ColorSensorV3(i2cport);
   // private static ColorMatch colormatcher = new ColorMatch();
    private static AHRS gyro = new AHRS(Port.kUSB);

    public static boolean initAngleNeeded = true;
    public static double initAngle = 0;
    public static boolean angleHeaderWritten = false;

    public static boolean initPosNeeded = true;
    public static double initPos = 0;
    public static boolean posHeaderWritten = false;

    public static double currentPos = 0;
    public static double currentAngle = 0;

    public static ArrayList<String> posColOne = new ArrayList<String>();
    public static ArrayList<String> posColTwo = new ArrayList<String>();

    public static ArrayList<String> angleColOne = new ArrayList<String>();
    public static ArrayList<String> angleColTwo = new ArrayList<String>();

    public static boolean currentRotationsNeeded = true;
    public static double currentRotations = 0;

    public static void setLeftMotors(double speed){
        motorLeft1.set(speed); 
        motorLeft2.set(speed);
    }
    public static void setRightMotors(double speed){
        motorRight1.set(-speed);
        motorRight2.set(-speed);
    }

    public static void move(double left, double right){
        setLeftMotors(left);
        setRightMotors(right);
    }
    public static void PIDMove(double distanceIN, double kP, double kD, double kI){
        /*if(currentRotationsNeeded){
            currentRotations = DriveTrain.getPosition();
            currentRotationsNeeded = false;
        }*/
        motorLeft1.getPIDController().setP(RobotMap.DriveTrain_P_Value);
        motorLeft1.getPIDController().setI(RobotMap.DriveTrain_I_Value);
        motorLeft1.getPIDController().setD(RobotMap.DriveTrain_D_Value);
        motorLeft1.getPIDController().setOutputRange(-0.5, 0.5);
        double rotations = distanceIN/Math.PI/RobotMap.DRIVE_WHEEL_DIAMETER*8.45;
        double reference = rotations*12.5;
        motorLeft1.getPIDController().setReference(reference, CANSparkMax.ControlType.kPosition, 0);
        motorRight1.follow(motorLeft1, true);
        motorRight2.follow(motorLeft1, true);
        motorLeft2.follow(motorLeft1);
        System.out.println("Encoder Value: " + DriveTrain.getPosition());


        if(initPosNeeded){
            initPos = DriveTrain.getPosition()/8.45*Math.PI*RobotMap.DRIVE_WHEEL_DIAMETER;
            initPosNeeded = false;
        }
        if(!posHeaderWritten){
            DriveTrain.writePositionOutput("Time", "Error");
            posHeaderWritten = true;
        }

        currentPos = DriveTrain.getPosition()/8.45*Math.PI*RobotMap.DRIVE_WHEEL_DIAMETER - initPos;
        double error = distanceIN - currentPos;

        DriveTrain.writePositionOutput(Timer.getFPGATimestamp() + "", error + "");
    }  

    public static boolean distanceCompleted(double distanceFT){ 
        if(initPosNeeded){
            return true;
        }
        if(Math.abs(DriveTrain.getPosition() - initPos - distanceFT) < RobotMap.CLEARENCE_SETPOINT){
            initPosNeeded = true;
            initPos = 0;
            return true;
        }
        return false;
    }

    public static AHRS getGyro(){
        return gyro;
    }

    public static double getGyroAngle(){
        return gyro.getAngle();
    }

    public static String getFirmWare(){
        return gyro.getFirmwareVersion();
    }

    public static void turn(double angle){        
        while(gyro.getAngle()<=angle){
            DriveTrain.move(0.3,0);

        } 
        DriveTrain.move(0,0);
    }
    
    //returns the velocity of the wheels in feet per second
    public static double getVelocity(){
        double vel = motorLeft1.getEncoder().getVelocity() * (RobotMap.DRIVE_WHEEL_DIAMETER/2) * (1/12) * 60;
        return vel;
    }

    public static I2C.Port getI2CPort(){
        return i2cport;
    }
    
    public static void setPosition(double position){
        motorLeft1.getEncoder().setPosition(position);
    }

    //returns the distance traveled in feet
    public static double getPosition(){
        //* RobotMap.DRIVE_WHEEL_DIAMETER * Math.PI * (1/12)
        return motorLeft1.getEncoder().getPosition();
    }

    public static void printVelocity(){
        System.out.println(getVelocity() + " feet/second");
    }
    public static void printPosition(){
        System.out.println(getPosition() + " feet");
    }

    public static void PIDturn(double setpointAngle, double kP, double kD, double kI, double iZone, double kF){
        if(initAngleNeeded){
            initAngle = gyro.getAngle();
            initAngleNeeded = false;
        }
        if(!angleHeaderWritten){
            DriveTrain.writeAngleOutput("Time", "Error");
            angleHeaderWritten = true;
        }

        currentAngle = gyro.getAngle() - initAngle;
        double error = setpointAngle - currentAngle;

        DriveTrain.writeAngleOutput(Timer.getFPGATimestamp() + "", error + "");

        double p = error * kP;
        double i = 0.0;
        if(Math.abs(error) <= iZone || iZone == 0.0) {
            i = error * kI;
        } 
        double d = gyro.getRate()*kD;
        double f = setpointAngle * kF;

        double output = p + i + d + f;
        if(output > 1){
            output = 1;
        } else if(output < -1){
            output = -1;
        }
       
        DriveTrain.move(output, -output);
    }

    public static boolean turnCompleted(double setpointAngle){ 
        if(initAngleNeeded){
            return true;
        }
        if(Math.abs(gyro.getAngle() - initAngle - setpointAngle) < RobotMap.CLEARENCE_SETPOINT){
            initAngleNeeded = true;
            initAngle = 0;
            return true;
        }
        return false;
    }

    public static void writeAngleOutput(String colOne, String colTwo){
        angleColOne.add(colOne);
        angleColTwo.add(colTwo);
    }

    public static void writePositionOutput(String colOne, String colTwo){
        posColOne.add(colOne);
        posColTwo.add(colTwo);
    }  
    
    public static void writePositionToCSV(){
        try (PrintWriter writer = new PrintWriter("DistanceErrorGraph.csv")) {
            System.out.println("We're good");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < posColOne.size(); i++){
                sb.append(posColOne.get(i));
                sb.append(',');
                sb.append(posColTwo.get(i));
                sb.append("\n");
            }
            writer.write(sb.toString());      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
    }    

    public static void writeAngleToCSV(){
        try (PrintWriter writer = new PrintWriter("AngleErrorGraph.csv")) {
            System.out.println("We're good");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < angleColOne.size(); i++){
                sb.append(angleColOne.get(i));
                sb.append(',');
                sb.append(angleColTwo.get(i));
                sb.append("\n");
            }
            writer.write(sb.toString());      
          } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
          }
    }    

  /*  public static Color getColor(){
        return colorsensor.getColor();
    }  

    public static boolean colorMatchCheck(Color c){
        ColorMatchResult match = colormatcher.matchClosestColor(DriveTrain.getColor());
        if(match.color == c && match.confidence >= RobotMap.COLOR_MATCH_CONFIDENCE_INTERVAL){
            return true;
        } else {
            return false;
        }*/

    //}

    @Override
    protected void initDefaultCommand() {
        
    }

}
    