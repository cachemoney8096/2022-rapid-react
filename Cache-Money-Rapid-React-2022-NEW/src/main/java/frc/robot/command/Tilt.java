package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.command.IntakeBalls;
import edu.wpi.first.wpilibj.Timer;
public class Tilt extends Command {
    public static boolean initTimeNeeded = true;
    public static double initTime = 0;
    public static double binitTime = 0;
    public boolean m_Up=false;

    public Tilt(Boolean Up){
        m_Up=Up;
        requires(Robot.m_intake);
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
        tilt();
       
    }

    @Override
    protected void end() {
        Intake.tilt(0);
        initTimeNeeded = true;
    }

    public static void tiltTrig(){
        double valTrig = Robot.m_oi.getDriverRawAxisXbox(2);
        double speed = valTrig * 0.3;
        if(speed < 0.1){
            speed = 0;
        } else if(speed < 0.2 && speed > 0.1){
            speed = 0.2;
        }
        if(Robot.m_oi.bluePressed()){
            Intake.tilt(-speed);
        } else {
            Intake.tilt(speed);
        }
    } 

    public void tilt(){
        double tiltPower=0;
        
        if(false==m_Up){
            tiltPower=0.125;
        }else{
            tiltPower=-0.075;
        }
        Intake.tilt(tiltPower);
        }
}
     
    



