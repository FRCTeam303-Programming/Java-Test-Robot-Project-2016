package org.usfirst.frc.team303.robot;
import edu.wpi.first.wpilibj.Timer;
public class ActionDriveSeconds implements Action{
	
	int time;
	int power;
	Timer t = new Timer();
	boolean firstRun=true;
	public ActionDriveSeconds(int timeC, int powerC){
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
