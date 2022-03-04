// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.command.TankDrive;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  public static final Subsystem Shooter = new Shooter();
  private String m_autoSelected;
  public static OI m_oi;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //Initializing Subsystems
  public static Subsystem m_intake= new Intake();
  public static Climb m_climb = new Climb();
  public static DriveTrain m_drivetrain = new DriveTrain();
  public static Shooter m_shooter = new Shooter();
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    SmartDashboard.putString("robot init", "executed");
    CameraServer.startAutomaticCapture();
    Robot.m_oi = new OI();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
       /*DriveTrain.pidMove();
       Intake.Limit();
       IntakeBalls.go();
       IntakeBalls.FrontIndex();
       IndexBalls.BackIndex();
        if(!DriveTrain.turnCompleted(90)){
         DriveTrain.PIDturn(90, RobotMap.TURN_kP, RobotMap.TURN_kD, RobotMap.TURN_kI, RobotMap.TURN_IZONE,RobotMap.TURN_kF );
        }
        
        System.out.println("Firmware Version: " + DriveTrain.getFirmWare());
        System.out.println("Gyro Angle: " + DriveTrain.getGyroAngle());
        TankDrive.PIDTurn(90);
       ShootBalls.ShootVel(RobotMap.Shooter_Vel_Value, RobotMap.Shooter_FF_Value);
       

        // Put custom auto code here
        break;*/
      case kDefaultAuto:
      default:
      //Alternate Between PID Move and PID Turn to see if it works
        DriveTrain.PIDMove(5, 2.5, 0.005, 0.0); //STEP ONE
        //DriveTrain.PIDturn(90, 2.5, 0.005, 0.0, 0.0, 0.0); //STEP TWO
        /*STEP THREE:
        if(DriveTrain.distanceCompleted(5)){
          DriveTrain.PIDturn(90, 2.5, 0.005, 0, 0, 0);
        }
        if(DriveTrain.turnCompleted(90)){
          DriveTrain.PIDMove(1, 2.5, 0.005, 0.0);
        }
        */
      /*
      COMPLETED AUTONOMOUS SEQUENCE
       DriveTrain.pidMove();
       Intake.Limit();
       IntakeBalls.go();
       IntakeBalls.FrontIndex();
       IndexBalls.BackIndex();
        if(!DriveTrain.turnCompleted(90)){
          DriveTrain.PIDturn(90, RobotMap.TURN_kP, RobotMap.TURN_kD, RobotMap.TURN_kI, RobotMap.TURN_IZONE,RobotMap.TURN_kF );
        }
        
        System.out.println("Firmware Version: " + DriveTrain.getFirmWare());
        System.out.println("Gyro Angle: " + DriveTrain.getGyroAngle());
        TankDrive.PIDTurn(90);
       ShootBalls.ShootVel(RobotMap.Shooter_Vel_Value, RobotMap.Shooter_FF_Value);
        */
       
      

        /* AUTONOMOUS SEQUENCE 
        1) MOVE BACK
        2) INTAKE BALL
        3) SHOOT BALLLS
        --- THE REST IS OPTIONAL FOR CIR
        4) TURN W/ GYRO
        5) MOVE FORWARD
        6) INTAKE BALL
        7) TURN W/GYRO
        8) SHOOT BALL  */
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    SmartDashboard.putString("teleop.intialize", "executed");
   
  }
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putString("teleop.periodic", "executed");
    Scheduler.getInstance().run();
    TankDrive.move();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
