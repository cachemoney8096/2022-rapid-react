package frc.robot.command;
import frc.robot.RobotMap;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Robot; 
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command{

  public static boolean arrived = false;
  public static boolean slightForward = false;
  public static boolean slightBackward = false;
  
  public TankDrive(){
      requires(Robot.m_drivetrain);
  }
     // Called when the command is initially scheduled.
  @Override
  // variables and mapping out buttons for the controller when it starts
  public void initialize() {

  }

  //variables and mapping out buttons called 50 times per second
  @Override
  public void execute() {
//The Left stick is speed control, the Right stick is turning control
    
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
  //most useful function vvvvvv
  public static int whatIsNinePlusTen() {
      return 21;
  }
  public static void move(){
    double speed= Robot.m_oi.getDriverRawAxis(RobotMap.LEFT_STICK_Y);
    double turn= Robot.m_oi.getDriverRawAxis(RobotMap.RIGHT_STICK_X);
    //The Left is pos the right is neg
    double right = speed + turn;
    double left = speed - turn;
    DriveTrain.move(left,right);
  }
  

  public static void test(double speed){
    DriveTrain.move(speed, speed);
  }

 /* public static void MovementUntilShadowLine(boolean ArmLatch) {
    if(!ArmLatch){
      if(!arrived){
        if(!DriveTrain.colorMatchCheck(RobotMap.SHADOW_RUNG_COLOR)){
          DriveTrain.move(0.2, 0.2); //TODO Test to see if these values are correct
        } else {
          DriveTrain.move(0.0, 0.0);
          arrived = true;
        }
      } else {
        if(!slightForward){
          DriveTrain.moveDistance(1.0); //TODO Add Encoder-Based Method in Java TODO Test to see if this is correct
        } else {
          DriveTrain.move(0.0, 0.0);
          slightForward = true;
        }
      }
    } else {
      if(slightForward && arrived){
        slightForward = false;
        arrived = false;
      }
      if(!slightBackward){
        DriveTrain.moveDistance(1.0); //TODO Add Encoder-Based Method in Java TODO Test to see if this is correct
      } else {
        slightBackward = true;
      }
    }
  }
*/
  public static boolean ClimbMovementFinished(){
    if(slightForward || slightBackward){
      return true;
    }
    return false;
  }
  
}
