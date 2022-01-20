package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem{
    public static Servo armMotor = new Servo(0);
    @Override
    protected void initDefaultCommand() {
    }
    public void goToDefault(){
        armMotor.setAngle(0.0);
    }
    public void setAngle(double degrees){
        armMotor.setAngle(degrees);
    }
    public void getAngle(){
        return armMotor.getAngle();
    }
}
