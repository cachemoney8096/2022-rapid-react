package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class IndexBalls extends Command{

    public static boolean initTimeNeeded = true;
    public static double initTime = 0;

    public static boolean binitTimeNeeded = true;
    public static double binitTime = 0;
    public static boolean bindexDone = false;

    public IndexBalls(){
        requires(Robot.m_index);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        if(initTimeNeeded){
            initTime = Timer.getFPGATimestamp();
            initTimeNeeded = false;
        }
        if(Timer.getFPGATimestamp() - initTime < 1){
            return false;
        }
        return true;
    }

    @Override
    protected void execute() {
        Intake.Limit();
        if(Robot.m_oi.yellowPressed()){
            Intake.FrontIndex(-0.4);
            Intake.BackIndex(-0.4);
        } else {
            Intake.FrontIndex(0.4);
            Intake.BackIndex(0.4);
        }
    }

    @Override
    protected void end() {
        Intake.FrontIndex(0);
        Intake.BackIndex(0);
        initTimeNeeded = true;
    }
    
    public static void BackIndex(){
        if(binitTimeNeeded){
            binitTime = Timer.getFPGATimestamp();
            binitTimeNeeded = false;
        }
        if(Timer.getFPGATimestamp() - binitTime < 1){
            Intake.BackIndex(0.25);
        } else {
            Intake.BackIndex(0.0);
            bindexDone = true;
        }
    }

    public static boolean BackIndexDone(){
        return bindexDone;
    }
}
