package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IntakeUnlock extends Command{

    public static boolean locking;
    public static double setpoint = 0.0;

    public IntakeUnlock(){
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        if(Math.abs((Intake.getLockAngle() - 50)) < 0.5){
            // System.out.println("TRUE");
            return true;
        }
        // System.out.println("FALSE");
        // System.out.println("Intake Angle: " + Intake.getLockAngle());
        return false;
    }

    @Override
    protected void execute() {
        /*
        if(locking){
            Intake.setLockAngle(90);
        } else  {
            Intake.setLockAngle(0.0);
        }
        System.out.println("Intake Angle: " + Intake.getLockAngle());*/
        Intake.setLockAngle(50);
        // System.out.println("Intake Angle: " + Intake.getLockAngle());
    }

    @Override
    protected void end() {
        
    }
}
