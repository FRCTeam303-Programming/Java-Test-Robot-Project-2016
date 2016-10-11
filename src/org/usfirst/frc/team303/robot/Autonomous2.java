package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous2 {

	/*
	e means encoders
	s means seconds
	n means navX
	
	every part of an assembeAutonomous() has two parts: 
	the limiter (put in the condition of the if statement) and the driver (put in the body of the if statement). 
	*/
	
	Timer mainS = new Timer();
	static double navXStart = Robot.drivebase.navXYaw;
	double totalDeltaYaw = 0;
	double oldNavX = 0;
	double newNavX = 0;
	double deltaNavX = 0;
	int[] sSwitch = {0, 3, 6, 9, 15}; //stores seconds to switch autonomous parts
	int[] eSwitch = {0, 300, 600, 900, 1500}; //stores encoder ticks to switch autonomous parts
	double[] nSwitch = {navXStart, angleCorrect(navXStart+90), angleCorrect(navXStart+145)}; //stores objective angles for navX turning, used to switch autonomous parts
	double[] nDeltaYaw = {0, 0, 0, 0, 0, 0}; // ex. first element is total space between element one and two of nSwitch
	boolean[] segmentsCompleted = {false, false, false, false, false, false};
	
	public void run() {
		switch(Robot.autoSelected1) {
			case "1" : {
				assembleAutonomousOne();
				break;
			}	
			case "2" : {
				break;
			}
			case "3" : {
				break;
			}
		}
		oldNavX = newNavX;
		newNavX = Robot.drivebase.navXYaw;
		deltaNavX = newNavX - oldNavX;
		totalDeltaYaw = totalDeltaYaw + (Math.abs(deltaNavX));
	}
	
	public Autonomous2() {
		Robot.drivebase.lDriveEnc.reset();
		Robot.drivebase.rDriveEnc.reset();
		Robot.drivebase.navX.zeroYaw();
		Robot.drivebase.navXYaw = 0;
		Robot.drivebase.lDriveEncDist = 0;
		Robot.drivebase.rDriveEncDist = 0;
		
		for(int i=0;i<nSwitch.length;i++) {
			if(i>0) {
				nDeltaYaw[i] = nDeltaYaw[i-1] + (nSwitch[i+1] - nSwitch[i]);
			}
			else {nDeltaYaw[i] = nSwitch[i+1];}
		}
		
		mainS.start();
	}
	
	//tier 1 methods 
	public void assembleAutonomousOne() { 
		
		segmentsCompleted[0] = getSegmentsFinished("s", 0);
		segmentsCompleted[1] = getSegmentsFinished("e", 1);
		segmentsCompleted[2] = getSegmentsFinished("n", 2);
				
		if(timeBySeconds(1, 0)) { //part 1
			driveForwardNoCorrection(0.80, 0.80);
		}		
		else if(timeByEncoders(1, 1)) { //part 2
			driveForwardNavXCorrection(0.75, 1, 1);
		}
		else if(timeByNavX(1, 2)) { //part 3
			turnL(0.60, true);
		}
		
	}
	
    //tier 2 methods
	//tier 2 method parameters run by 'name(upper target, int currentOperation)'
	
	public boolean timeByEncoders(int eHigh, int currentOperation) { //used for delta encoders
		return (checkSegmentsFinished(currentOperation) && isBetween(Math.abs(Robot.drivebase.lDriveEncDist), 0, eSwitch[eHigh])); //used for encoder distance)
	}
	
	public boolean timeBySeconds(int sHigh, int currentOperation) { //used for delta seconds
		return (checkSegmentsFinished(currentOperation) && isBetween(mainS.get(), 0, sSwitch[sHigh]));
	}
	
	public boolean timeByNavX(int nTarget, int currentOperation) { //used for delta navX
		return (checkSegmentsFinished(currentOperation) && isBetween(Robot.drivebase.navXYaw, nSwitch[nTarget]-2, nSwitch[nTarget]+2));
	}
	
	
	//tier 3 methods
	
	public boolean getSegmentsFinished(String esn, int number) {
		switch(esn) {
			case "s" : {
				return (mainS.get()>sSwitch[number]);
			}
			case "e" : {
				return (Math.abs(Robot.drivebase.lDriveEncDist)>eSwitch[number]);
			}
			case "n" : {
				return (totalDeltaYaw>nDeltaYaw[number]);
			}
			default : {
				return false;
			}
		}
	}
	
	public boolean checkSegmentsFinished(int currentOperation) {
		for(int i=0;i<currentOperation;i++) {
			if(!segmentsCompleted[i]) {
				return false;
			}
		}
		return true;
	}
	
	public double angleCorrect(double theta) { //converts 0_to_360 to -180_to_180 scale
		if(isBetween(theta, -180, 180)) {
			return theta;
		}
		else {
			return (theta<-180) ? theta+360 : theta-360;
		}
	}
	
	public void driveForwardNoCorrection(double lSpeed, double rSpeed) {
		Robot.drivebase.drive(lSpeed, rSpeed);
	}
	
	public void turnL(double speed, boolean left) {
		Robot.drivebase.drive((left ? speed*-1 : speed), (left ? speed : speed*-1));
	}
	
	public void driveForwardNavXCorrection(double powSetpoint, double angleDifference, double tuningConstant) {
		Robot.drivebase.drive((powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant)));
	}
	
	public boolean isBetween(double var, double bottom, double top) {
		return (var<top && var>bottom);
	}
	
}
