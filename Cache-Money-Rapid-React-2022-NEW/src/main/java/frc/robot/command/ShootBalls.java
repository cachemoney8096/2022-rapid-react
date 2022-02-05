package frc.robot.command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Shooter;



public class ShootBalls extends Command{

    public ShootBalls(){
        requires(Robot.Shooter);
        Shooter.ShootBraindead(.5);

    }
     // Called when the command is initially scheduled.
  @Override
  // variables and mapping out buttons for the controller when it starts
  public void initialize() {
    SmartDashboard.putString("ShootBalls.intialize", "executed");

  }

  //variables and mapping out buttons called 50 times per second
  @Override
  public void execute() {
    SmartDashboard.putString("ShootBalls.execute", "before shootBraindead");

    Shooter.ShootBraindead(.5);
    SmartDashboard.putString("ShootBalls.execute", "after shootBraindead");
    
    

    
  }
  // When the stop button is hit, the motors turn off
  @Override
  public void end() {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
  
  public static void ShootFast(){
    Shooter.ShootBraindead(.45);
    //Shooter.configureShooterPIDValues(kP, kI, kD, kF, CMV, MotionAcceleration, SmoothingStrength);
    //Shooter.setMotionMagic(targetPos, kF);
    

  }

  

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
      distance = (RobotMap.UPPER_HUB_HEIGHT - RobotMap.HEIGHT_TO_LIMELIGHT) / Math.tan(RobotMap.DEFAULT_LIMELIGHT_HEIGHT + Shooter.getVerticalOffsetAngle());
    } else {
      distance = (RobotMap.LOWER_HUB_HEIGHT - RobotMap.HEIGHT_TO_LIMELIGHT) / Math.tan(RobotMap.DEFAULT_LIMELIGHT_HEIGHT + Shooter.getVerticalOffsetAngle());
    }
    return distance;
  }

  public static void ShootVel(double targetVel, double kF){
    Shooter.setVelocity(targetVel, kF);
  }

  

  
}
