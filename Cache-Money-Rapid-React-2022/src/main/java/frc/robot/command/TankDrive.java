package frc.robot.command;
import frc.robot.RobotMap;
import frc.robot.Robot; 
import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command{

    public TankDrive(){
        requires(Robot.driveTrain);
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
    double turn= Robot.m_oi.getDriverRawAxis(RobotMap.RIGHT_STICK_Y);
    //The Left is pos the right is neg
    double right = speed + turn;
    double left = speed - turn;
    DriveTrain.move(left,right);


  }
  
}
