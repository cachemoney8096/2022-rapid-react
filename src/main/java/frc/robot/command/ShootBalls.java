package frc.robot.command;
import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command; 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class ShootBalls extends Command{
  public static boolean TwoBalls = false;
  public static boolean[] shotprogression = new boolean[RobotMap.SHOT_PROGRESSION_LENGTH];
  public static boolean startTimeNeeded = true;
  public static double startTime = 0.0;
  private static boolean shootOn;
  public ShootBalls(boolean twoballs, boolean shoot){
    requires(Robot.m_shooter);
    //requires(Robot.m_intake);
    TwoBalls = twoballs;
    shootOn = shoot;
  }
  // Called when the command is initially scheduled.
  @Override
  // variables and mapping out buttons for the controller when it starts
  public void initialize() {
    SmartDashboard.putString("ShootBalls.intialize", "executed");
    if(shootOn){
      Shooter.ShootBraindead(0.48);
    } else {
      Shooter.ShootBraindead(0);
    }
  }

  //variables and mapping out buttons called 50 times per second
  @Override
  public void execute() {
    

    /*if(TwoBalls){
      if(!shotprogression[0]){
        ShootBalls.ShootTime(RobotMap.SHOT_TIME, 0);
      } else if(!shotprogression[1]){
        ShootBalls.RestTime(RobotMap.SHOT_REST_TIME, 1);
      } else if(!shotprogression[2]){
        ShootBalls.ShootTime(RobotMap.SHOT_TIME, 2);
      }
    } else {
      Shooter.ShootBraindead(0.6);
    }*/

    
  }
  // When the stop button is hit, the motors turn off
  @Override
  public void end() {
    Shooter.ShootBraindead(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    /*if(TwoBalls){
      return shotprogression[RobotMap.SHOT_PROGRESSION_LENGTH - 1];
    } else {
      return shotprogression[0];
    }*/
    return true;
  }

  /*public double SpeedAdjustment(){
    double adjust= 0.5; 
    if(OI.RightBumpButton.get()){
        adjust+=.1;
    }
    else if(OI.LeftBumpButton.get()){
        adjust-=.1;
    }
    return adjust;
 */   
//}

  public static double getInitialVelocity(boolean upper){
    double dH = 0;
    if(upper){
      dH = RobotMap.UPPER_HUB_HEIGHT - RobotMap.HEIGHT_TO_SHOOTER;
    } else {
      dH = RobotMap.LOWER_HUB_HEIGHT - RobotMap.HEIGHT_TO_SHOOTER;
    }
    double d = ShootBalls.getShotDistance(upper);
    double theta = RobotMap.SHOOTER_ANGLE;
    double G = RobotMap.ACCELERATION_DUE_TO_GRAVITY;
    double v = d*Math.sqrt(G)*Math.cos(theta)/Math.sqrt(2*d*Math.sin(theta)*Math.cos(theta)+2*dH);
    return v;
  }

  public static double getShotDistance(boolean upper){
    double distance = 0.0;
    if(upper){
      distance = (RobotMap.UPPER_HUB_HEIGHT - RobotMap.HEIGHT_TO_LIMELIGHT) / Math.tan(RobotMap.DEFAULT_LIMELIGHT_ANGLE + Shooter.getVerticalOffsetAngle());
    } else {
      distance = (RobotMap.LOWER_HUB_HEIGHT - RobotMap.HEIGHT_TO_LIMELIGHT) / Math.tan(RobotMap.DEFAULT_LIMELIGHT_ANGLE + Shooter.getVerticalOffsetAngle());
    }
    return distance;
  }

  public static void ShootVel(double targetVel, double kF){
    double angularVelocity = targetVel * RobotMap.CONVERT_LINEAR_VELOCITY_TO_ANGULAR_FALCON;
    //Intake.Limit();
    IndexBalls.BackIndex();
    Shooter.setVelocity(angularVelocity, kF);
  }

  public static void shootTrig(){
    double trigVal = Robot.m_oi.getDriverRawAxisJoystick(3);
    double speed = trigVal *0.48;
    if(speed < 0.4 && speed > 0.2){
      speed = 0.48;
    }
    Shooter.ShootBraindead(speed);
  }



  public static void ShootTime(double shotlength, int arrayIndex){
    if(startTimeNeeded){
      startTime = Timer.getFPGATimestamp();
      startTimeNeeded = false;
    }
    if(Timer.getFPGATimestamp() - startTime < shotlength){
      ShootBalls.ShootVel(getInitialVelocity(true), 0);
    } else {
      shotprogression[arrayIndex] = true;
      Shooter.ShootBraindead(0.0);
      startTimeNeeded = true;
      startTime = 0.0;
    }
  }

  public static void RestTime(double restlength, int arrayIndex){
    if(startTimeNeeded){
      startTime = Timer.getFPGATimestamp();
      startTimeNeeded = false;
    }
    if(Timer.getFPGATimestamp() - startTime < restlength){
      Shooter.ShootBraindead(0.0);
    } else {
      shotprogression[arrayIndex] = true;
      Shooter.ShootBraindead(0.0);
      startTimeNeeded = true;
      startTime = 0.0;
    }
  }

  

  
}
