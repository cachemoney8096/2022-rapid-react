package frc.robot.command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Climb;

public class AutomatedClimb extends Command {
    public static double initClimbPos_Ext = 0;
    public static double initClimbPos_Piv = 0;
    public static boolean[] auto = new boolean[RobotMap.CLIMB_PROCESS_LENGTH];
    public static boolean Up = false;
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
        Climb.setExtensionPosition(0.0);
    }
    
    @Override
    protected boolean isFinished() {
        /*
        if(Up){
            return auto[RobotMap.CLIMB_PROCESS_LENGTH - 1];
        } else {
            return auto[1];
        }*/
        return false;
    }

    @Override
    protected void execute() {
        /*
        if(TranscendentRung){
            TranscendentRung();
        } else {
            MidRung();
        }
        */
        if(Robot.m_oi.startPressed()){
            if(Robot.m_oi.redPressed()){
                Climb.setExtensionLeft(0.3);
            }else if(Robot.m_oi.greenPressed()){
                Climb.setExtensionRight(0.3);
            } else {
                Climb.setExtensionSpeed(0.3);
            }
        } else {
            if(Robot.m_oi.redPressed()){
                Climb.setExtensionLeft(-0.3);
            } else if(Robot.m_oi.greenPressed()){
                Climb.setExtensionRight(-0.3);
            } else {
                Climb.setExtensionSpeed(-0.3);
            }
        }
    }
    


    @Override
    protected void end() {
        Climb.setExtensionSpeed(0.0);
        //Climb.setPivotMotorSpeed(0.0);
    }

    public static void MidRung(){
        Climb.PIDExtend(60, 0.05, 0, 0);
        /*if(!auto[0]){
            Climb.PIDExtend(60, 0.05, 0, 0);
        } else if(!auto[1]){
            Climb.PIDExtend(-50, 0.05, 0, 0);
        } */
    }

    public static void TranscendentRung(){  
        if(!auto[0]){
            //  TankDrive.MovementUntilShadowLine(false);
              if(TankDrive.ClimbMovementFinished()){
                  auto[0] = true;
              }
          } else if(!auto[1]){
              AutomatedClimb.AutomatedExtension(1, RobotMap.ARM_EXT_LENGTH_ONE, true);
          } else if(!auto[2]){
            //  TankDrive.MovementUntilShadowLine(true);
              if(TankDrive.ClimbMovementFinished()){
                  auto[2] = true;
              }
          } else if(!auto[3]){
              AutomatedClimb.AutomatedExtension(3, RobotMap.ARM_EXT_LENGTH_TWO, false);
          } else if(!auto[4]){
              AutomatedClimb.AutomatedPivot(4, RobotMap.ARM_PIV_ANGLE_ONE, true);
          } else if(!auto[5]){
              AutomatedClimb.AutomatedExtension(5, RobotMap.ARM_EXT_LENGTH_THREE, true);
          } else if(!auto[6]){
              AutomatedClimb.AutomatedPivot(6, RobotMap.ARM_PIV_ANGLE_TWO, false);
          } else if(!auto[7]){
              AutomatedClimb.AutomatedExtension(7, RobotMap.ARM_EXT_LENGTH_FOUR, false);
          } else if(!auto[8]){
              AutomatedClimb.AutomatedExtension(8, RobotMap.ARM_EXT_LENGTH_FIVE, false);
          } else if(!auto[9]){
              AutomatedClimb.AutomatedPivot(9, RobotMap.ARM_PIV_ANGLE_THREE, true);
          } else if(!auto[10]){
              AutomatedClimb.AutomatedExtension(10, RobotMap.ARM_EXT_LENGTH_SIX, true);
          } else if(!auto[11]){
              AutomatedClimb.AutomatedPivot(11, RobotMap.ARM_PIV_ANGLE_FOUR, false);
          } else if(!auto[12]){
              AutomatedClimb.AutomatedExtension(12, RobotMap.ARM_EXT_LENGTH_SEVEN, false);
          } else if(!auto[13]){
              AutomatedClimb.AutomatedExtension(13, RobotMap.ARM_EXT_LENGTH_EIGHT, false);
          } 
    }

    public static boolean ArmExtenstionFinished(double targetPos, double initPosition) {
        /*if(Math.abs(Climb.getExtensionPosition()-initPosition) == Math.abs(targetPos)){
            return true;
        }*/
        return false;
    }

    public static boolean ArmRotationFinished(double targetAngle, double initAngle) {
        if(Math.abs(Climb.getPivotPosition() - initAngle) == Math.abs(targetAngle)){
            return true;
        }
        return false;
    }

    public static void AutomatedExtension(int indexLocation, double TargetPos, boolean forward){
       // initClimbPos_Ext = Climb.getExtensionPosition();
        AutomatedClimb.MoveArm(TargetPos, forward);
        if(AutomatedClimb.ArmExtenstionFinished(TargetPos, initClimbPos_Ext)){
            auto[indexLocation] = true; 
            Climb.setExtensionSpeed(0.0);
        }
        initClimbPos_Ext = 0;
    }

    public static void AutomatedPivot(int indexLocation, double targetAngle, boolean forward){
        initClimbPos_Piv = Climb.getPivotPosition();
        AutomatedClimb.RotateArm(targetAngle, forward);
        if(AutomatedClimb.ArmRotationFinished(targetAngle, initClimbPos_Piv)){
            auto[indexLocation] = true;
            Climb.setPivotMotorSpeed(0.0);
        } 
        initClimbPos_Piv = 0;
    }

    public static void MoveArm(double distance, boolean forward){
        int dir_coeff = 0;
        if(forward){dir_coeff=1;} else {dir_coeff=-1;}
        //Climb.setExtensionMotionMagic(distance*RobotMap.CLIMB_DISTANCE_CONVERSION_FACTOR*dir_coeff, RobotMap.EXTENSION_COEFFICIENT_kF); 
    }

    public static void RotateArm(double distance, boolean forward){
        int dir_coeff = 0;
        if(forward){dir_coeff=1;} else {dir_coeff=-1;}
        Climb.setPivotMotionMagic(distance*RobotMap.CLIMB_ROTATION_CONVERSION_FACTOR*dir_coeff, RobotMap.PIVOT_COEFFICIENT_kF);
    }
    
}
