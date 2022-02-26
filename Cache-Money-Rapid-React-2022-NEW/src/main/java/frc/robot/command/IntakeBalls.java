package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.command.IntakeBalls;
import edu.wpi.first.wpilibj.Timer;
public class IntakeBalls extends Command{

    public IntakeBalls(){
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
        go();
    

    }

    @Override
    protected void end() {
        super.end();
    }
    public static void go(){
   double currentTime =Timer.getFPGATimestamp();
   while(currentTime<1){
   Intake.go(0.5);}
   FrontIndex();


  }

  public static void tilt(){
    
   Intake.tilt(0.5);
   

}
public static void FrontIndex(){
    double currentTime= Timer.getFPGATimestamp();
    while(currentTime<1){
     Intake.FrontIndex(0.25);
    }
    Intake.FrontIndex(0);
    
 
   }

    
}
