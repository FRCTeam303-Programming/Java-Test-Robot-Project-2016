package org.usfirst.frc.team303.robot;

public class setIntake implements Action{
	double setpoint;
	public setIntake(double setpointC){
		setpoint=setpointC;
	}
	public void run(){
		Robot.intake.intakeSet(setpoint);
	}
	public boolean isFinished(){
		return true;
	}
}
