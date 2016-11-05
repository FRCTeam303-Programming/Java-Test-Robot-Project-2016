package org.usfirst.frc.team303.robot;

public class ClawSetpoint {
	double setpoint;
	public ClawSetpoint(double setpointC){
		setpoint=setpointC;
	}
	boolean isFinished(){
		return true;
	}
	public void run(){
		Robot.claw.clawSet(setpoint);
	}
}
