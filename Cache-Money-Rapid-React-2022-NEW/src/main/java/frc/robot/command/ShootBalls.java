package frc.robot.command;
import frc.robot.Robot; 
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
    Shooter.configureShooterPIDValues(kP, kI, kD, kF, CMV, MotionAcceleration, SmoothingStrength);
    Shooter.setMotionMagic(targetPos, kF);
    

  }

  public static void ShootVel(double targetVel, double kF){
    Shooter.setVelocity(targetVel, kF);
  }

  
}
