package frc.robot.command;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class SpeedAdjustment extends Command{

    public static double speedAdjustment = 0;
    public static boolean buttonPressed = false;

    public SpeedAdjustment(){
        requires(Robot.m_shooter);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        return buttonPressed;
    }

    @Override
    protected void execute() {
        if(Robot.m_oi.leftStickPressed()){
            Shooter.speedAdjust(-0.05);
        } else {
            Shooter.speedAdjust(0.05);
        }
        buttonPressed = true;
        

    }

    @Override
    protected void end() {
        buttonPressed = false;
        speedAdjustment = 0;
    }
    
}