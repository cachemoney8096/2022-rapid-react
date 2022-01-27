package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SampleSubsystem extends Subsystem {
    public static Servo armMotor = new Servo(0);
	@Override
	protected void initDefaultCommand() {
		
    }
    
    public static void moveArm(double angle){
        armMotor.setAngle(angle);
    }
    
}
