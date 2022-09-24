package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.command.TankDrive;
//put these in individual functions or classes or whatever
public class RobotMap {
  //Drive Train Motor IDs
  public static final int  MOTOR_LEFT_1_ID = 9;  
  public static final int  MOTOR_LEFT_2_ID = 11;
  public static final int  MOTOR_RIGHT_1_ID = 10;
  public static final int  MOTOR_RIGHT_2_ID = 8;
  public static final double DriveTrain_P_Value= 0.3;
  public static final double DriveTrain_I_Value= 0.00;
  public static final double DriveTrain_D_Value= 0.005;
  public static final double DRIVE_WHEEL_DIAMETER = 4;
  public static final double DRIVE_WHEEL_CIRCUMFERENCE = Math.PI * DRIVE_WHEEL_DIAMETER;
  //diameter is in inches

 
  //Shooter Motor IDs
  //Motor 1 = left, motor 2 = right
  public static final int  SHOOTER_MOTOR_1_ID = TankDrive.whatIsNinePlusTen() - 1; //20
  public static final int  SHOOTER_MOTOR_2_ID = TankDrive.whatIsNinePlusTen(); //21
  public static final int Shooter_Vel_Value= 0; 
  public static final int Shooter_FF_Value= 0;  
  public static final int BraindeadShootValue = 0;

  //Intake Motor IDs
  public static final int LEFT_TILT_INTAKE_MOTOR= 7;
  public static final int RIGHT_TILT_INTAKE_MOTOR = 6;
  public static final int GO_INTAKE_MOTOR= 14;  


  //Indexer Motor IDs
  public static final int FrontIndexMotorLeft= 3;
  public static final int FrontIndexMotorRight= 1;
  public static final int BackIndexMotorRight= 2;
  public static final int BackIndexMotorLeft= 4;



  //Climb Motor IDs
  public static final int LEFT_EXT_MOTOR = 15;
  public static final int RIGHT_EXT_MOTOR = 16;
  public static final int RIGHT_PIVOT_MOTOR = 12;
  public static final int LEFT_PIVOT_MOTOR = 13;


  //Controller IDs
  public static final int  DRIVERCONTROLLER = 0;
  public static final int  BUTTONCONTROLLER = 1;

  public static final int RIGHT_STICK_X = 4;
  public static final int LEFT_STICK_Y = 1;
  public static final int LEFT_TRIGGER = 2;
  public static final int RIGHT_TRIGGER = 3;
  public static final int RIGHT_STICK_Y= 5;
  
  public static final int BUTTON_GREEN = 1;
  public static final int BUTTON_RED = 2;
  public static final int BUTTON_BLUE = 3;
  public static final int BUTTON_YELLOW = 4;
  public static final int BUTTON_LEFTBUMP = 5;
  public final static int BUTTON_RIGHTBUMP = 6;
  public final static int BUTTON_BACK = 7;
  public final static int BUTTON_START = 8;
  public final static int BUTTON_LEFTSTICK = 9;
  public final static int BUTTON_RIGHTSTICK = 10;


  //Climb Parameters
  public static final int CLIMB_PROCESS_LENGTH = 14;
  public static final Color SHADOW_RUNG_COLOR = new Color(20, 20, 20);
  public static final double COLOR_MATCH_CONFIDENCE_INTERVAL = 0.1; 


  //Climb Extension PID Parameters
  public static final double EXTENSION_COEFFICIENT_kP = 0;
  public static final double EXTENSION_COEFFICIENT_kI = 0;
  public static final double EXTENSION_COEFFICIENT_kD = 0;
  public static final double EXTENSION_COEFFICIENT_kF = 0;
  public static final double EXTENSION_COEFFICIENT_CMV = 0;
  public static final double EXTENSION_COEFFICIENT_MOTION_ACCELERATION = 0;
  public static final int EXTENSION_COEFFICIENT_SMOOTHING_STRENGTH = 0;

  //Climb Pivot PID Parameters
  public static final double PIVOT_COEFFICIENT_kP = 0;
  public static final double PIVOT_COEFFICIENT_kI = 0;
  public static final double PIVOT_COEFFICIENT_kD = 0;
  public static final double PIVOT_COEFFICIENT_kF = 0;
  public static final double PIVOT_COEFFICIENT_CMV = 0;
  public static final double PIVOT_COEFFICIENT_MOTION_ACCELERATION = 0;
  public static final int PIVOT_COEFFICIENT_SMOOTHING_STRENGTH = 0;

  // Climb Extension Parameter Lengths
  public static final double ARM_EXT_LENGTH_ONE = 66;
  public static final double ARM_EXT_LENGTH_TWO = 66;
  public static final double ARM_EXT_LENGTH_THREE = 61.04;
  public static final double ARM_EXT_LENGTH_FOUR = 2.00;

  public static final double ARM_EXT_LENGTH_FIVE = 59.04;
  public static final double ARM_EXT_LENGTH_SIX = 61.04;
  public static final double ARM_EXT_LENGTH_SEVEN = 2.00;
  public static final double ARM_EXT_LENGTH_EIGHT = 59.04;

  // Climb Pivot Parameter Angles
  public static final double ARM_PIV_ANGLE_ONE = 26;
  public static final double ARM_PIV_ANGLE_TWO = 1.67;

  public static final double ARM_PIV_ANGLE_THREE = 26;
  public static final double ARM_PIV_ANGLE_FOUR = 1.67;

  //Turn PID Parameters
  public static final double TURN_kP = 0;
  public static final double TURN_kI = 0;
  public static final double TURN_kD = 0;
  public static final double TURN_IZONE= 0;
  public static final double TURN_kF = 0;


  //Shooter Parameters
  public static final double UPPER_HUB_HEIGHT = 2.581275;
  public static final double HEIGHT_TO_LIMELIGHT = 0.2032;
  public static final double DEFAULT_LIMELIGHT_ANGLE = 60.0;
  public static final int LOWER_HUB_HEIGHT = 0;
  public static final int HEIGHT_TO_SHOOTER = 0;
  public static final double SHOOTER_ANGLE = 0;
  public static final double ACCELERATION_DUE_TO_GRAVITY = 9.803;
  public static final double CLEARENCE_SETPOINT = 0.01;
  public static final int SHOT_PROGRESSION_LENGTH = 3;
  public static final double SHOT_TIME = 0.5;
  public static final double SHOT_REST_TIME = 0.5;
  public static final double SHOOTER_kP = 0.2;
  public static final double SHOOTER_kI = 0.0005;
  public static final double SHOOTER_kD = 3.5;
  public static final double SHOOTER_kF = 0.05;
  public static final double SHOOTER_IZONE = 200.0;

  //Conversion Factors
  public static final double CONVERT_ANGULAR_VELOCITY_TO_LINEAR_FALCON = 20*Math.PI*0.050038/2048;
  public static final double CONVERT_LINEAR_VELOCITY_TO_ANGULAR_FALCON = 1/CONVERT_ANGULAR_VELOCITY_TO_LINEAR_FALCON;
  public static final double EXT_WHEEL_DIAMETER = 2.5; 
  public static final double CLIMB_DISTANCE_CONVERSION_FACTOR = 2048/(2*Math.PI*EXT_WHEEL_DIAMETER);
  public static final double CLIMB_ROTATION_CONVERSION_FACTOR = 2048/360;

  //Test Motor
  public static final int MOTOR_TEST_ID = 25;

  //AUTONOMOUS VALUES
  public static final int AUTONOMOUS_LENGTH = 20;
  public static final double SHOT_CHARGE_TIME = 1.5;
  public static final double AUTO_SHOT_STRENGTH = 0.49;
  public static final double TILT_SHOT_TIME = 0.3;
  public static final double AUTO_SHOT_TIME = 1;
  public static final double TILT_UP_TIME = 0.405;
  public static final double TILT_DOWN_TIME = 0.75;
  public static final double TILT_DOWN_POWER = 0.175;  
  public static final double TILT_UP_POWER = 0.29;
  public static final double INDEX_TIME = 2.5;
  public static final double MOVE_CORRECTION_TIME = 1.0;

  //Path Following Values (All Units are in SI Units -- Meters, Seconds, Volts)
  public static final double TRACK_WIDTH = 0;
  public static final DifferentialDriveKinematics DRIVE_KINEMATICS =  new DifferentialDriveKinematics(TRACK_WIDTH);

  public static double kA_DRIVE;
  public static double kV_DRIVE;
  public static double kS_DRIVE;
  public static final double kP_DRIVE_VEL = 0;
  /*
  public static final int kEncoderCPR = 1024;
  public static final double kWheelDiameterMeters = 0.15;
  public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (double) kEncoderCPR;
*/
  public static final double MAX_SPEED = 3;
  public static final double MAX_ACCELERATION = 3;
  public static final double k_RAMSETE_B = 2;
  public static final double k_RAMSETE_ZETA = 0.7;

  //Intake Locks
  public static final int SERVO_DIO_PORT = 0;
  public static final double SERVO_REST_ANGLE = 0;
  public static final double SERVO_LOCK_ANGLE = 90;




  



}
