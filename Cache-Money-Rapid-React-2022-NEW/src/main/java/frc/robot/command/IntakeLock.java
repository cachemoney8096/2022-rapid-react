package frc.robot.command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class IntakeLock extends Command{

    public static boolean locking = true;
    public static double setpoint = 0.0;

    public IntakeLock(boolean lockMode){
        requires(Robot.m_lock);
        locking = lockMode;
        if(lockMode){
            setpoint = RobotMap.SERVO_LOCK_ANGLE;
        } else {
            setpoint = RobotMap.SERVO_REST_ANGLE;
        }
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        if(Math.abs((Intake.getLockAngle() - setpoint)) < 0.5){
            return true;
        }
        return false;
    }

    @Override
    protected void execute() {
        if(locking){
            Intake.setLockAngle(90);
        } else  {
            Intake.setLockAngle(0.0);
        }
    }

    @Override
    protected void end() {
        
    }
}
