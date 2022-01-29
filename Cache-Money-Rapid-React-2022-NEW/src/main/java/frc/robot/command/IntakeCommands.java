package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCommands extends Command{

    public IntakeCommands(){
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
    }

    @Override
    protected void end() {
        super.end();
    }
    public static void go(){
   
   Intake.go(0.5);

  }

  public static void tilt(){
    
   Intake.tilt(0.5);
   

}


    
}
