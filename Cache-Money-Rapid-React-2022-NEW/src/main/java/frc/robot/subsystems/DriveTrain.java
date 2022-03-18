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
    public static CANSparkMax motorLeft1 = new CANSparkMax(RobotMap.MOTOR_LEFT_1_ID, MotorType.kBrushless);
    public static CANSparkMax motorLeft2 = new CANSparkMax(RobotMap.MOTOR_LEFT_2_ID, MotorType.kBrushless);
    public static CANSparkMax motorRight1 = new CANSparkMax(RobotMap.MOTOR_RIGHT_1_ID, MotorType.kBrushless);
    public static CANSparkMax motorRight2 = new CANSparkMax(RobotMap.MOTOR_RIGHT_2_ID, MotorType.kBrushless);
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

    public static boolean turnDone = false;

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

    public static void turnMove(double output){
        motorRight1.follow(motorLeft1, false);
        motorRight2.follow(motorLeft1, false);
        motorLeft2.follow(motorLeft1, false);
        motorLeft1.set(output);        
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
        double error = (setpointAngle - currentAngle);

        DriveTrain.writeAngleOutput(Timer.getFPGATimestamp() + "", error + "");
  
        double p = error * kP;
        double d = -1*error/Math.abs(error)*Math.abs(gyro.getRate())*kD;
        /*double i = 0.0;
        if(Math.abs(error) <= iZone || iZone == 0.0) {
            i = error * kI;
        } 
        double f = setpointAngle * kF; */

        double output = p; // + d; //+ i + d + f;
        System.out.println("Initial Output: " + output);
        if(output > 0.5){
            output = 0.5;
        } else if(output < -0.5){
            output = -0.5;
        }
        double n = output;
        output = Math.abs(error/setpointAngle)*n;
        if(Math.abs(error) < 0.5 || turnDone){
            output = 0;
            turnDone = true;
        } else if (Math.abs(output) < 0.07) {
            output = output/Math.abs(output)*0.07;
        }
        System.out.println("Gyro Angle: " + gyro.getAngle());
        System.out.println("Gyro Rate: " + gyro.getRate());
        System.out.println("Error: " +  error);
        System.out.println("kP: " + p);
        System.out.println("kD: " + d);
        System.out.println("Final Output: " + output);

       
        DriveTrain.turnMove(output);
    }

    public static boolean distanceCompleted(){ 
        if(DriveTrain.getVelocity() < 0.01 && Math.abs(DriveTrain.getPosition()) > 20.0){
            DriveTrain.setPosition(0);
            DriveTrain.resetGyro();
            return true;
        }
        return false;
    }

    public static boolean turnCompleted(){ 
        if(turnDone){
            DriveTrain.setPosition(0);
            DriveTrain.resetGyro();
            turnDone = false;
            return true;
        } else {
            return false;
        }
    }

    public static void setPosition(double position){
        motorLeft1.getEncoder().setPosition(position);
    }

    public static void resetGyro(){
        gyro.reset();
    }

    public static void resetMotors(){
        motorLeft1.restoreFactoryDefaults();
        motorLeft2.restoreFactoryDefaults();
        motorRight1.restoreFactoryDefaults();
        motorRight2.restoreFactoryDefaults();
    }

    public static void autoFollow(){
        motorRight1.follow(motorLeft1, true);
        motorRight2.follow(motorLeft1, true);
        motorLeft2.follow(motorLeft1);
    }

    public static double getPosition(){
        //* RobotMap.DRIVE_WHEEL_DIAMETER * Math.PI * (1/12)
        return motorLeft1.getEncoder().getPosition();
    }

    //returns the velocity of the wheels in feet per second
    public static double getVelocity(){
        return motorLeft1.getEncoder().getVelocity();
    }

    public static double getGyroAngle(){
        return gyro.getAngle();
    }

    public static double getGyroRate(){
        return gyro.getRate();
    }

    public static I2C.Port getI2CPort(){
        return i2cport;
    }

    public static AHRS getGyro(){
        return gyro;
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
        /*try (PrintWriter writer = new PrintWriter("DistanceErrorGraph.csv")) {
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
          }*/
    }    

    public static void writeAngleToCSV(){
        /*
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
          */
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

    /*Work With Mr. Gupta:
        double p = error/setpointAngle * kP;
        double d = -(gyro.getRate())*kD;
        /*double i = 0.0;
        if(Math.abs(error) <= iZone || iZone == 0.0) {
            i = error * kI;
        } 
        double f = setpointAngle * kF;

        double output = p + d; */ //*/

    @Override
    protected void initDefaultCommand() {
        
    }

}
    