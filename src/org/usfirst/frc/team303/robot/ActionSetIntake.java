package org.usfirst.frc.team303.robot;

public class ActionSetIntake implements Action{
	double setpoint;
	public ActionSetIntake(double setpointC){
		setpoint=setpointC;
	}
	public void run(){
		Robot.intake.intakeSet(setpoint);
	}
	public boolean isFinished(){
		return true;
	}
}
