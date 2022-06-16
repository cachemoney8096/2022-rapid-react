package frc.robot;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.DriveTrain;

public class RobotContainer {

    private final DriveTrain m_drivetrain = new DriveTrain();

    public Command getAutonomousCommand(){
        var autoVoltageConstraint =
            new DifferentialDriveVoltageConstraint(
                new SimpleMotorFeedforward(RobotMap.kS_DRIVE, RobotMap.kV_DRIVE, RobotMap.kA_DRIVE),
                RobotMap.DRIVE_KINEMATICS,
                10);
        
        TrajectoryConfig config = new TrajectoryConfig(RobotMap.MAX_SPEED, RobotMap.MAX_ACCELERATION).setKinematics(RobotMap.DRIVE_KINEMATICS);

        Trajectory exampleTrajectory = 
            TrajectoryGenerator.generateTrajectory(
                new Pose2d(0, 0, new Rotation2d(0)), 
                List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
                new Pose2d(3, 0, new Rotation2d(0)), 
                config);

        /*RamseteCommand ramseteCommand = 
            new RamseteCommand(
                exampleTrajectory,
                m_drivetrain::getPose, 
                new RamseteController(RobotMap.k_RAMSETE_B, RobotMap.k_RAMSETE_ZETA),
                new SimpleMotorFeedforward(RobotMap.kS_DRIVE, RobotMap.kV_DRIVE, RobotMap.kA_DRIVE),
                RobotMap.DRIVE_KINEMATICS,
                new PIDController(RobotMap.kP_DRIVE_VEL, 0, 0),
                new PIDController(RobotMap.kPDriveVel, 0, 0),
                m_drivetrain::tankDriveVolts,
                m_drivetrain);

        m_drivetrain.resetOdometry(exampleTrajectory.getInitialPose());
        */
        //return ramseteCommand.andThen(() -> m_drivetrain.tankDriveVolts(0, 0));
          return null;  

    }
    
}
