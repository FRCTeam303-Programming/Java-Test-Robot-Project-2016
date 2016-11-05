package org.usfirst.frc.team303.robot;
import edu.wpi.first.wpilibj.Timer;

public class Wait implements Action{
	int time;
	boolean firstRun;
	Timer t = new Timer();
	public Wait(int timeC){
		time=timeC;
	}
	public boolean isFinished(){
		return t.get()>=time;
	}
	public void run(){
		if(firstRun){
			t.start();
			firstRun=false;
		}
	}
}
