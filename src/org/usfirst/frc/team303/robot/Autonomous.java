package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous {
	
	//MAIN METHODS BELOW
	public void lowBarAuto() {
		putClawDown();
		Robot.pneumatics.gearShift.set(false);
		moveForwardSeconds(1500);
	}
	
	public void rockWallAuto() {
		putClawDown();
	}
	
	
	
	// HELPER METHODS BELOW
	public void moveForwardSeconds(double seconds) {
		Timer timer = new Timer();
		timer.start();
		double startTime = timer.get();
		
		while((startTime + seconds) >= timer.get()) {
			Robot.drivebase.drive(0.75, 0.75);
		}
		Robot.drivebase.drive(0, 0);
		timer.stop();
	}
	
	public void moveForwardEncoder(double encoderTicks) {
		Robot.drivebase.updateSensors();
		double encStart = Robot.drivebase.rDriveEnc.getDistance();
		while((encStart + encoderTicks) >= Robot.drivebase.rDriveEnc.getDistance()) {
			Robot.drivebase.drive(0.75, 0.75);
			Robot.drivebase.updateSensors();
		}
		Robot.drivebase.drive(0, 0);
	}
	
	@SuppressWarnings("static-access")
	public void putClawDown() {
		double clawStart = Robot.claw.claw.getPosition(); //this method complains about static access
		double acc = 0;
		while(!Robot.claw.claw.isFwdLimitSwitchClosed()) { //this method complains about static access
			Robot.claw.clawSet(clawStart + acc);
			acc = acc + 0.0001;
		}
	}
	
	public void turnDownForNavX(double degrees) { //TODO finish this method- copy from LabVIEW
		Robot.drivebase.updateSensors();
		double navXStart = Robot.drivebase.navX.getYaw();
	}
	
}

















