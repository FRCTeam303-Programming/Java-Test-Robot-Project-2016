package org.usfirst.frc.team303.robot;
import edu.wpi.first.wpilibj.Timer;
public class DriveSeconds implements Action{
	
	int time;
	int power;
	Timer t = new Timer();
	boolean firstRun=true;
	public DriveSeconds(int timeC, int powerC){
		time=timeC;
		power=powerC;
		
		
	}
	public boolean isFinished(){
		if(t.get()>=time)
			return true;
		return false;
	}
	public void run(){
		if(firstRun){
			t.start();
			firstRun=false;
		}
		autoDrive(power);
	}
	public void autoDrive(int speed){
		Robot.drivebase.drive(-speed,speed);
	}
}
