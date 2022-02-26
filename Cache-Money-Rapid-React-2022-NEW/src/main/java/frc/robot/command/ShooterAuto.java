package frc.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.command.IntakeBalls;
import frc.robot.subsystems.Intake;

public class ShooterAuto extends Command {
  
    public ShooterAuto(){
        requires(Robot.m_shooter);
        requires(Robot.m_drivetrain);
        requires(Robot.m_intake);
       
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void execute() {
        
         Shooter.ShootBraindead(0.5);


    }
    @Override
    protected void end() {
        super.end();
    }
      public static void ShootSimple(){
       DriveTrain.pidMove();
       Intake.Limit();
       IntakeBalls.go();
       IntakeBalls.FrontIndex();
       IndexBalls.BackIndex();
        if(!DriveTrain.turnCompleted(90)){
          DriveTrain.PIDturn(90, 2, 0, 0, 0, 0);
        }
        
        System.out.println("Firmware Version: " + DriveTrain.getFirmWare());
        System.out.println("Gyro Angle: " + DriveTrain.getGyroAngle());
        TankDrive.PIDTurn(90);
       ShootBalls.ShootVel(targetVel, kF);

         //value for this will be the same for everything once we figure it out
      // DriveTrain.turn(90);
       //ShootBalls.ShootVel(targetVel, kF);  //Value for this will vary based on where we are shooting
      }

      

    }

    

    

   

    
    // TODO research ControlMode.Position for encoders and distance input

    
    

