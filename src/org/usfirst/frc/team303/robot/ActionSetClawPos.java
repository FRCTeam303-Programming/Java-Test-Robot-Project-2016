package org.usfirst.frc.team303.robot;

public class ActionSetClawPos {
	double setpoint;
	public ActionSetClawPos(double setpointC){
		setpoint=setpointC;
	}
	boolean isFinished(){
		return (Claw.claw.getError()<0.05);
	}
	public void run(){
		Robot.claw.clawSet(setpoint);
	}
}
