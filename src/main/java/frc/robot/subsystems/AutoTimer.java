package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class AutoTimer extends Subsystem { 
    public static boolean timerRunning = false;  
    public static double startTime = 0;
    //Motor Variable setup
    public static boolean timePassed(double seconds){
        if(!timerRunning){
            timerRunning = true;
            startTime = Timer.getFPGATimestamp();
        }
        if(Timer.getFPGATimestamp() - startTime < seconds){
            return false;
        } else {
            startTime = 0;
            timerRunning = false;
            return true;
        }
    }



    @Override
    protected void initDefaultCommand() {

    }
}
    