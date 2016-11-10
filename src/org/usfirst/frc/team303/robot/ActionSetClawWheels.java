package org.usfirst.frc.team303.robot;

public class ActionSetClawWheels implements Action {
	double setPoint;
	public ActionSetClawWheels(double setPointC){
		setPoint=setPointC;
	}
	public boolean isFinished(){ //TODO add condition, must test tolernace first
		return true;
	}
	public void run(){
		Robot.clawwheels.clawWheelsSet(setPoint);
	}
}
