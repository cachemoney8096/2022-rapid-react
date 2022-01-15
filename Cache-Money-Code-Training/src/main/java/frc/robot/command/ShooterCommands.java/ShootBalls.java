package frc.robot.command;
import frc.robot.RobotMap;
import frc.robot.Robot; 
import edu.wpi.first.wpilibj.command.Command;

public class ShootBalls extends Command{

    public ShootBalls(){
        requires(Robot.Shooter);
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
  
  public static void ShootFast(){
    
   Shooter.setmotor1(0.75);
   Shooter.setmotor2(0.75);

  }

  public static void ShootSlow(){
    
   Shooter.setmotor1(0.5);
   Shooter.setmotor2(0.5);
   

}
  
}
