package org.usfirst.frc.team303.robot;

public class ActionSetPiston implements Action{
	boolean state;
	public ActionSetPiston(boolean stateC) {
		state = stateC;
	}
	public boolean isFinished() {
		return true;
	}
	public void run() {
		Robot.pneumatics.pneumaticsCtrl(state, false);
	}

	
}
