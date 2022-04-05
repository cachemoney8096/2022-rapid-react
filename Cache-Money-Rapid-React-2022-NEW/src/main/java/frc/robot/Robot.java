// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.cameraserver.CameraServer;
import frc.robot.subsystems.AutoTimer;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.command.IndexBalls;
import frc.robot.command.ShootBalls;
import frc.robot.command.TankDrive;
import frc.robot.command.Tilt;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  public static boolean ballRight = true;
  public static OI m_oi;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //Initializing Subsystems
  public static Intake m_intake= new Intake();
  public static Intake m_index = new Intake();
  public static Intake m_lock = new Intake();
  public static Climb m_climb = new Climb();
  public static DriveTrain m_drivetrain = new DriveTrain();
  public static Shooter m_shooter = new Shooter();
  public static AutoTimer m_autotimer = new AutoTimer();

  public static boolean[] AutoSequence = new boolean[RobotMap.AUTONOMOUS_LENGTH];
  
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
    Climb.setBrakeMode();
    DriveTrain.setPosition(0);
    DriveTrain.resetGyro();
    Intake.setTiltBrakeMode();
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
    DriveTrain.setPosition(0);
    DriveTrain.resetGyro();
    Climb.setBrakeMode();
    for(int i = 0; i < 14; i++){
      AutoSequence[i] = false;
    }
    AutoSequence[0] = false;
    AutoSequence[1] = false;
    AutoSequence[2] = false;
    //Intake.setTiltBrakeMode();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        MidwestAutoSequence();
        //CompetitionAutoSequence(false);
        //DriveTrain.PIDMove(40, 0.005, 0.0, 0.0);
        //DriveTrain.PIDMove(-12, 0.005, 0.0, 0.0);
        //System.out.println("Encoder Value: " + DriveTrain.getPosition());
        //DriveTrain.PIDturn(-90, 0.05, 0.5, 0.0, 0.0, 0.0); //STEP TWO
      case kDefaultAuto:
        //DriveTrain.PIDMove(40, 0.005, 0.0, 0.0);
        //DriveTrain.PIDMove(-12, 0.005, 0.0, 0.0);
        //System.out.println("Encoder Value: " + DriveTrain.getPosition());
        MidwestAutoSequence();
        //CompetitionAutoSequence(true);
        //DriveTrain.PIDturn(-90, 0.05, 0.5, 0.0, 0.0, 0.0); //STEP TWO
      default:
        //if(DriveTrain.distanceCompleted()){
        MidwestAutoSequence();
        //DriveTrain.PIDMove(-200, 0.005, 0.0, 0.0);
        //}
        /*if(ballRight){
          CompetitionAutoSequence(true);
        } else {
          CompetitionAutoSequence(false);
        }*/

        //DriveTrain.PIDMove(40.5, 0.005, 0.0, 0.0);
        //DriveTrain.PIDMove(-12, 0.005, 0.0, 0.0);
        System.out.println("Encoder Value: " + DriveTrain.getPosition()); //STEP ONE
        //DriveTrain.PIDturn(-90, 0.05, 0.5, 0.0, 0.0, 0.0); //STEP TWO

        /*
        //INTAKE TEST
        if(DriveTrain.distanceCompleted(1)){
        Intake.Limit();
        IntakeBalls.go();
        IntakeBalls.FrontIndex();
        IndexBalls.BackIndex();
        }

        /* AUTONOMOUS SEQUENCE 
        1) Shoot
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

  /*public static void CIRAutoSequence(){
    if(!AutoSequence[0]){
      if(AutoTimer.timePassed(RobotMap.SHOT_CHARGE_TIME)){
        AutoSequence[0] = true;
      } else {
        Shooter.ShootBraindead(RobotMap.AUTO_SHOT_STRENGTH);
        Intake.tilt(0.3);
      }
    } else if(!AutoSequence[1]){
      if(AutoTimer.timePassed(RobotMap.AUTO_SHOT_TIME)){
        AutoSequence[1] = true;
        Intake.BackIndex(0.0);
        Shooter.ShootBraindead(0.0);
      } else {
        Intake.Limit();
        Intake.BackIndex(0.4);
      }
    } else if(!AutoSequence[2]){
      if(AutoTimer.timePassed(RobotMap.TILT_UP_TIME)){
        AutoSequence[2] = true;
        Intake.tilt(0.0);
      } else {
        Intake.tilt(RobotMap.TILT_UP_POWER);
      }
    } else if(!AutoSequence[3]){
      if(AutoTimer.timePassed(3)){
        DriveTrain.move(0, 0);
        AutoSequence[3] = true;
      } else {
        DriveTrain.move(-0.5, -0.5);
      }
    }
  }*/

  public static void MidwestAutoSequence(){
    if(!AutoSequence[0]){
      if(DriveTrain.distanceCompleted()){
        AutoSequence[0] = true;
        DriveTrain.move(0,0);
      } else {
        DriveTrain.PIDMove(40.5, 0.005, 0.0, 0.0);
      }
    } else if(!AutoSequence[1]){
      if(AutoTimer.timePassed(RobotMap.TILT_DOWN_TIME)){
        AutoSequence[1] = true;
        Intake.tilt(0.0);
      } else {
        Intake.tilt(RobotMap.TILT_DOWN_POWER);
      }
    } else if(!AutoSequence[2]){
      if(AutoTimer.timePassed(0.6)){
        AutoSequence[2] = true;
      }
    }else if(!AutoSequence[3]){
      if(AutoTimer.timePassed(RobotMap.INDEX_TIME)){
        AutoSequence[3] = true;
        Intake.go(0.0);
        Intake.FrontIndex(0.0);
        DriveTrain.move(0,0);
        Intake.tilt(0.0);
      } else {
        Intake.go(0.65);
        Intake.FrontIndex(0.4);
        DriveTrain.move(0.06, 0.06);
        Intake.tilt(0.075);
      }
    } else if(!AutoSequence[4]){
      if(AutoTimer.timePassed(RobotMap.TILT_UP_TIME)){
        AutoSequence[4] = true;
        Intake.tilt(0.0);
        DriveTrain.resetGyro();
      } else {
        Intake.tilt(-RobotMap.TILT_UP_POWER);
      }
    } else if(!AutoSequence[5]){
      if(DriveTrain.turnCompleted()){
        AutoSequence[5] = true;
        DriveTrain.resetGyro();
        DriveTrain.setPosition(0.0);
        DriveTrain.resetMotors();
      } else {
        DriveTrain.PIDturn(170, 0.2, 0.5, 0, 0, 0);
      }
    } else if(!AutoSequence[6]){
      if(AutoTimer.timePassed(0.85)){
        DriveTrain.move(0, 0);
        AutoSequence[6] = true;
      } else {
        //DriveTrain.move(0, 0);
        DriveTrain.move(0.75, 0.75);
      } 
    } else if(!AutoSequence[7]){
      if(AutoTimer.timePassed(RobotMap.TILT_SHOT_TIME)){
        AutoSequence[7] = true;
        Intake.tilt(0.0);
      } else {
        Shooter.ShootBraindead(RobotMap.AUTO_SHOT_STRENGTH);
        Intake.tilt(RobotMap.TILT_DOWN_POWER);
      }
    } else if(!AutoSequence[8]){
      if(AutoTimer.timePassed((RobotMap.SHOT_CHARGE_TIME - RobotMap.TILT_SHOT_TIME))){
        AutoSequence[8] = true;
      } else {
        Intake.tilt(0.0);
      }
    } else if(!AutoSequence[9]){
      if(AutoTimer.timePassed(RobotMap.AUTO_SHOT_TIME)){
        AutoSequence[9] = true;
        Intake.BackIndex(0.0);
      } else {
        Intake.Limit();
        Intake.BackIndex(0.4);
      }
    } else if(!AutoSequence[10]){
      if(AutoTimer.timePassed(0.5)){
        AutoSequence[10] = true;
      }
    } else if(!AutoSequence[11]){
      if(AutoTimer.timePassed(RobotMap.AUTO_SHOT_TIME)){
        AutoSequence[11] = true;
        Intake.BackIndex(0.0);
        Intake.FrontIndex(0.0);
        Shooter.ShootBraindead(0.0);
      } else {
        Intake.Limit();
        Intake.FrontIndex(0.4);
        Intake.BackIndex(0.4);
      }
    } else if(!AutoSequence[12]){
      if(AutoTimer.timePassed(RobotMap.TILT_UP_TIME)){
        AutoSequence[12] = true;
        Intake.tilt(0.0);
      } else {
        Intake.tilt(-RobotMap.TILT_UP_POWER);
      }
    } /*else if(!AutoSequence[13]){
      if(AutoTimer.timePassed(3)){
        DriveTrain.move(0, 0);
        AutoSequence[13] = true;
      } else {
        DriveTrain.move(-0.5, -0.5);
      }
    }*/
    int yuh = 0;
    while (AutoSequence[yuh] == true){
      yuh++;
    }
    System.out.println("Auto Sequence: " + yuh);
  }

  /*public static void CompetitionAutoSequence(boolean turnRight){
    if(!AutoSequence[0]){
      if(AutoTimer.timePassed(RobotMap.SHOT_CHARGE_TIME)){
        AutoSequence[0] = true;
      } else {
        Intake.tilt(0.3);
      }
    } else if(!AutoSequence[1]){
      if(AutoTimer.timePassed(RobotMap.AUTO_SHOT_TIME)){
        AutoSequence[1] = true;
        Intake.BackIndex(0.0);
        Shooter.ShootBraindead(0.0);
      } else {
        Intake.Limit();
        Intake.BackIndex(0.4);
      }
    } else if(!AutoSequence[2]){
      if(AutoTimer.timePassed(RobotMap.TILT_UP_TIME)){
        AutoSequence[2] = true;
        Intake.tilt(0.0);
      } else {
        Intake.tilt(RobotMap.TILT_UP_POWER);
      }
    } else if(!AutoSequence[3]){
      if(DriveTrain.distanceCompleted()){
        AutoSequence[3] = true;
        DriveTrain.resetGyro();
      } else {
        DriveTrain.PIDMove(-200, 0.005, 0.0, 0.0);
      }
    } else if(!AutoSequence[4]){
      if(DriveTrain.turnCompleted()){
        AutoSequence[4] = true;
      } else {
        int kDir = 1;
        if(!turnRight){
          kDir = -1;
        }
        DriveTrain.PIDturn(kDir*90, 0.05, 0.5, 0, 0, 0);
      }
    } else if(!AutoSequence[5]){
      //if(DriveTrain.distanceCompleted()){
        //AutoSequence[5] = true;
      //} else {
      DriveTrain.PIDMove(-200, 0.005, 0, 0);
      //}
    } else if(!AutoSequence[6]){
      if(AutoTimer.timePassed(RobotMap.TILT_DOWN_TIME)){
        AutoSequence[6] = true;
        Intake.tilt(0.0);
      } else {
        Intake.go(0.75);
        Intake.tilt(-RobotMap.TILT_DOWN_POWER);
      }
    } else if(!AutoSequence[7]){
      if(AutoTimer.timePassed(RobotMap.INDEX_TIME)){
        AutoSequence[7] = true;
        Intake.go(0.0);
        Intake.BackIndex(0.0);
      } else {
        Intake.go(0.75);
        Intake.BackIndex(0.4);
      }
    } else if(!AutoSequence[8]){
      if(AutoTimer.timePassed(RobotMap.TILT_UP_TIME)){
        AutoSequence[8] = true;
        Intake.tilt(0.0);
        DriveTrain.resetGyro();
      } else {
        Intake.tilt(RobotMap.TILT_UP_POWER);
      }
    } else if(!AutoSequence[9]){
      if(DriveTrain.turnCompleted()){
        AutoSequence[9] = true;
      } else {
        int kDir = -1;
        if(!turnRight){
          kDir = 1;
        }
        DriveTrain.PIDturn(90*kDir, 0.05, 0.5, 0, 0, 0);
      }
    } else if(!AutoSequence[10]){
      if(DriveTrain.distanceCompleted()){
        AutoSequence[10] = true;
      } else {
        DriveTrain.PIDMove(116.17, 0.005, 0.0, 0.0);
      }
    } else if(!AutoSequence[11]){
      if(AutoTimer.timePassed(RobotMap.MOVE_CORRECTION_TIME)){
        AutoSequence[11] = true;
      } else {
        DriveTrain.move(0.2,0.2);
      }
    } else if(!AutoSequence[12]){
      if(AutoTimer.timePassed(RobotMap.SHOT_CHARGE_TIME)){
        AutoSequence[12] = true;
        Intake.tilt(0.0);
      } else {
        Intake.tilt(-RobotMap.TILT_DOWN_POWER);
        Shooter.ShootBraindead(0.5);
      }
    } else if(!AutoSequence[13]){
      if(AutoTimer.timePassed(RobotMap.SHOT_TIME)){
        AutoSequence[13] = true;
      } else {
        Intake.Limit();
        Intake.BackIndex(0.4);
      }
    } else if(!AutoSequence[14]){
      if(AutoTimer.timePassed(0.3)){
        AutoSequence[14] = true;
      } else {
        Shooter.ShootBraindead(0.0);
      }
    }
    int yuh = 0;
    while (AutoSequence[yuh] == true){
      yuh++;
    }
    System.out.println("Auto Sequence: " + yuh);
    System.out.println("Velocity: " + DriveTrain.getGyroRate());
    System.out.println("Position: " + DriveTrain.getGyroAngle());
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    SmartDashboard.putString("teleop.intialize", "executed");
    DriveTrain.resetMotors();
    Climb.setBrakeMode();
    Intake.setTiltBrakeMode();
  }
  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putString("teleop.periodic", "executed");
    Scheduler.getInstance().run();
    Tilt.tiltTrig();
    ShootBalls.shootTrig();
    TankDrive.move();
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    DriveTrain.writePositionToCSV();
    DriveTrain.writeAngleToCSV();
    for(int i = 0; i < 14; i++){
      AutoSequence[i] = false;
    }
    DriveTrain.resetGyro();
    DriveTrain.setPosition(0);
  }
  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
    DriveTrain.resetGyro();
    DriveTrain.setPosition(0);
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
