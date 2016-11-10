package org.usfirst.frc.team303.robot;
import edu.wpi.first.wpilibj.Timer;

public class Wait implements Action{
	double time;
	boolean firstRun;
	Timer t = new Timer();
	public Wait(double timeC){
		time=timeC;
		firstRun=true;
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
