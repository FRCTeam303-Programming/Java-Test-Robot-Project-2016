package org.usfirst.frc.team303.robot;

public class ActionSetIntakeWheels implements Action {
	double setPoint;
	public ActionSetIntakeWheels(double setPointC){
		setPoint=setPointC;
	}
	public boolean isFinished(){
		return true;
	}
	public void run(){
		Robot.intakewheels.set(setPoint);
	}

}
