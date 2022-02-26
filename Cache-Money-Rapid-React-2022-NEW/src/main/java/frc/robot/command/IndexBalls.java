package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class IndexBalls extends Command{

    public IndexBalls(){
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
        super.execute();
        Intake.Limit();
        FrontIndex();
        

    }

    @Override
    protected void end() {
        super.end();
    }
    public static void go(){
   
   Intake.go(0.5);


  }

  public static void tilt(){
   double currentTime=Timer.getFPGATimestamp(); 
   while(currentTime<1)
   Intake.tilt(0.5);
   

}

public static void FrontIndex(){
   double currentTime= Timer.getFPGATimestamp();
   while(currentTime<1){
    Intake.FrontIndex(0.25);
   }
   Intake.FrontIndex(0);
   

  }
public static void BackIndex(){
    double currentTime= Timer.getFPGATimestamp();
    while(currentTime<1){
     Intake.BackIndex(0.25);
    }
    Intake.BackIndex(0);
 
   }
    
}
