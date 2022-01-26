package frc.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climb;

public class AutomatedClimb extends Command {
    public static boolean[] auto = new boolean[RobotMap.CLIMB_PROCESS_LENGTH];
    //Move Towards Rung
    //Extend Arm
    //Latch Onto Bar
    //Retract Arm
    //Rotate Arm
    //Extend Arm
    //Rotate Arm
    //Latch Onto Bar
    //Retract Arm
    //Rotate Arm
    //Extend Arm
    //Rotate Arm
    //Latch Onto Bar
    //Retract Arm
    public AutomatedClimb(){
        requires(Robot.m_climb);
        requires(Robot.m_drivetrain);
    }

    @Override
    protected void initialize() {
        super.initialize();
    }
    
    @Override
    protected boolean isFinished() {
        return auto[RobotMap.CLIMB_PROCESS_LENGTH - 1];
    }

    @Override
    protected void execute() {
        if(!auto[0]){
            TankDrive.MovementUntilShadowLine();
            auto[0] = true;
        } else if(!auto[1]){
            AutomatedClimb.MoveArm(1, true); //TODO Need to fill out distance
            auto[1] = true; //TODO Does one method called for MoveArm run for the whole method or is it repeated?
        } else if(!auto[2]){
            // TODO Addd Arm Latch Mechanism
            auto[2] = true;
        } else if(!auto[3]){
            AutomatedClimb.MoveArm(1, false); //TODO Need to fill out distance
            auto[3] = true;
        } else if(!auto[4]){
            AutomatedClimb.RotateArm(1, false); //TODO need to fill out angle
            auto[4] = true;
        } else if(!auto[5]){
            auto[5] = true;
        } else if(!auto[6]){
            auto[6] = true;
        } else if(!auto[7]){
            auto[7] = true;
        } else if(!auto[8]){
            auto[8] = true;
        } else if(!auto[9]){
            auto[9] = true;
        } else if(!auto[10]){
            auto[10] = true;
        } else if(!auto[11]){
            auto[11] = true;
        } else if(!auto[12]){
            auto[12] = true;
        } else if(!auto[13]){
            auto[13] = true;
        } else if(!auto[14]){
            auto[14] = true;
        }
    }

    @Override
    protected void end() {
        super.end();
    }

    public static void MoveArm(double distance, boolean forward){
        int dir_coeff = 0;
        if(forward){dir_coeff=1;} else {dir_coeff=-1;}
        Climb.setExtensionMotorPosition(distance*RobotMap.CLIMB_DISTANCE_CONVERSION_FACTOR*dir_coeff);
    }
    // TODO research ControlMode.Position for encoders and distance input

    public static void RotateArm(double distance, boolean forward){
        int dir_coeff = 0;
        if(forward){dir_coeff=1;} else {dir_coeff=-1;}
        Climb.setPivotMotorPosition(distance*RobotMap.CLIMB_DISTANCE_CONVERSION_FACTOR*dir_coeff);
    }


    
}
