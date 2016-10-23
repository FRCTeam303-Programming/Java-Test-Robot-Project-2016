package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous3 {

	/*
	e means encoders
	s means seconds
	n means navX
	*/
	
	Timer mainS = new Timer();
	double navXStart = Robot.drivebase.navXYaw;
	double totalDeltaYaw = 0;
	double oldNavX = 0;
	double newNavX = 0;
	double deltaNavX = 0;
	
	public void run() {
		
		oldNavX = newNavX;
		newNavX = Robot.drivebase.navXYaw;
		deltaNavX = newNavX - oldNavX;
		totalDeltaYaw = totalDeltaYaw + (Math.abs(deltaNavX));
		
		switch(Robot.autoSelected1) {
			case "Feature Test" : {
				//assembleAutonomousOne();
				break;
			}	
			case "Low Bar / Low Goal" : {
				assembleAutonomousTwo();
				break;
			}
			case "3" : {
				break;
			}
		}
	}
	
	public Autonomous3() {
		Robot.drivebase.lDriveEnc.reset();
		Robot.drivebase.rDriveEnc.reset();
		Robot.drivebase.navX.zeroYaw();
		Robot.drivebase.navXYaw = 0;
		Robot.drivebase.lDriveEncDist = 0;
		Robot.drivebase.rDriveEncDist = 0;
		
		mainS.stop();
		mainS.reset();
		mainS.start();
	}
	
	//tier 1 methods 
	//every part of an assembeAutonomous() has two parts: 
	//the limiter (put in the condition of the if statement) and the driver (put in the body of the if statement). 
	
	/*public void assembleAutonomousOne() {
		
		//MUST UPDATE VALUES HERE
		
		if(timeBySeconds(0, 3)) { //part 1
			driveForwardNoCorrection(0.80, 0.80);
		}		
		else if(timeByEncoders(1, 1)) { //part 2
			driveForwardNavXCorrection(0.75, 1, 1);
		}
		else if(timeByNavX(1, 2)) { //part 3
			turnL(0.60, true);
		}
		
	}*/
	
	public void assembleAutonomousTwo() {
		int[] eSwitch = {300};
		int[] sSwitch = {4, 8};
		double[] nSwitch = {0, 90, 270}; //stores objective angles for navX turning, used to switch autonomous parts
		double[] nDeltaYaw = {0, 0, 0, 0, 0, 0}; // ex. first element is total space between element one and two of nSwitch
		double[] navXFollowingYaw = {0, 30, 90};
		fillNavXDeltaData(nSwitch, nDeltaYaw);
		
		if(timeByEncoders(0, eSwitch[0])) { //drive through low bar
			driveForwardNavXCorrection(0.7, navXFollowingYaw[0], 0.1);
			autoIntake(0.4);
		}
		
		if(timeByNavX(0, 1, nDeltaYaw, nSwitch)) { //angle at low goal
			turnL(0.6, false);
			autoIntake(0.4);
		}
		
		/*if(timeByEncoders()) { //drive to goal
			
		}*/
		
	}
	
    //tier 2 methods
	//tier 2 method parameters run by 'name(lowBound, highBound)'
	
	/* tier 2 methods have two parts */
	
	public boolean timeByEncoders(int atLeast, int atMost) { //used for delta encoders
		//return (Math.abs(Robot.drivebase.lDriveEncDist) > eSwitch[eLow]) && (Math.abs(Robot.drivebase.lDriveEncDist) < eSwitch[eHigh]); //used for encoder distance)
		return isBetween(Math.abs(Robot.drivebase.lDriveEncDist), atLeast, atMost);
	}
	
	public boolean timeBySeconds(int atLeast, int atMost) { //used for delta seconds
		//return ((mainS.get()>sSwitch[sLow]) && (mainS.get() < sSwitch[sHigh]));
		return isBetween(mainS.get(), atLeast, atMost);
		
	}
	
	public boolean timeByNavX(int nTarget, int idealTotalDistanceTurnt, double[] nDeltaYaw, double[] nSwitch) { //used for delta navX
		return (figureNavXFinished(idealTotalDistanceTurnt, nDeltaYaw) && isBetween(Robot.drivebase.navXYaw, nSwitch[nTarget]-2, nSwitch[nTarget]+2));
	}
	
	
	//tier 3 methods
	
	public void fillNavXDeltaData(double[] nSwitch, double[] nDeltaYaw) {
		for(int i=0;i<nSwitch.length;i++) {
			if(i==nSwitch.length - 1) {
				nDeltaYaw[i] = 0;
			}
			else if(i>0) {
				nDeltaYaw[i] = nDeltaYaw[i-1] + (nSwitch[i+1] - nSwitch[i]);
			}
			else {nDeltaYaw[i] = nSwitch[i+1];}
		}
	}
	
	public boolean figureNavXFinished(int index, double[] nDeltaYaw) {
		return (totalDeltaYaw > nDeltaYaw[index]);
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
		autoDrive(lSpeed, rSpeed);
	}
	
	public void autoDrive(double lSpeed, double rSpeed) {
		Robot.drivebase.drive(-1*lSpeed, rSpeed);
	}
	
	public void autoIntake(double pos) {
		Robot.intake.intakeSet(pos);
	}
	
	public void turnL(double speed, boolean left) {
		autoDrive((left ? speed*-1 : speed), (left ? speed : speed*-1));
	}
	
	public void driveForwardNavXCorrection(double powSetpoint, double idealAngle, double tuningConstant) { //the larger the tuning constant, the harder the correction
		double angleDifference = idealAngle - Robot.drivebase.navXYaw;
		autoDrive((powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant)));
	}
	
	public boolean isBetween(double var, double bottom, double top) {
		return (var<top && var>bottom);
	}
	
}
