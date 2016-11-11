package org.usfirst.frc.team303.robot;

public interface Action {
	
	public boolean isFinished(); //returns true if action is finished
	public void run(); // runs action, called every 20ms if it is the current action

}
