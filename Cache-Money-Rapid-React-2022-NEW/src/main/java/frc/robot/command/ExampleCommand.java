package frc.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExampleCommand extends Command{

    public ExampleCommand(){
        requires(Robot.subsystem);
    }

    @Override
    protected void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    protected void execute() {
        // TODO Auto-generated method stub
        super.execute();
    }

    @Override
    protected void end() {
        // TODO Auto-generated method stub
        super.end();
    }


    
}
