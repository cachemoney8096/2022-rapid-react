package frc.robot.command;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArmLift extends Command{
    public void ArmLift(){
        requires Robot.subsystem;
    }

    @Override
    protected void execute(){}
    @Override
    protected boolean isFinished(){
        return false;
    }
    protected void armUp(){
        double angle = Arm.getAngle();
        Arm.setAngle(pos += 0.1);
    }
    protected void armDown(){
        double pos = armMotor.getPosition();
        armMotor.setPosition(pos -= 0.1);
    }
}