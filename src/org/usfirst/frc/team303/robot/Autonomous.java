package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Timer;

public class Autonomous extends Thread {
	
	//instead of timer.delay(x), what about thread.wait(x) ?
	
	public void run() {
		switch(Robot.autoSelected1) {
		case Robot.lowBar:
			lowBarAuto();
			break;
		case Robot.rockWall:
			//Put rock wall / rough terrain code here
			rockWallAuto();
			break;
		case Robot.featureTest:
			//Put feature test code here
			featureTest();
			break;
		default:
			//Put default auto code here
			break;
		}
		
		System.out.println("doge approves of incorrect multi-threading.");
		interrupt();
		
	}
	
	
	//MAIN METHODS BELOW
	public void lowBarAuto() {
		putClawDown();
		Robot.pneumatics.gearShift.set(false);
		driveStraightEncoder(500, 0.7, 0.01, -0.15); //encoderTicks, powSetpoint, tuningConstant, intakePos
	}
	
	public void rockWallAuto() {
		putClawDown();
		Robot.pneumatics.gearShift.set(true);
		driveStraightEncoder(800, 0.75, 0.01, -0.15); //encoderTicks, powSetpoint, tuningConstant, intakePos
		Robot.pneumatics.gearShift.set(false);
	}
	
	public void featureTest() {
		putClawDown();
		driveStraightEncoder(250, 0.7, 0.01, -0.15); //encoderTicks, powSetpoint, tuningConstant, intakePos
		turnDownForNavX(90, 0.65, true, -0.15); //degrees, speed, left?, intakePos
	}
	
	
	// HELPER METHODS BELOW
	public void driveForwardSeconds(double seconds) {
		Timer timer = new Timer();
		timer.start();
		double startTime = timer.get();
		
		while((startTime + seconds) >= timer.get()) {
			Robot.drivebase.drive(0.75, 0.75);
			//System.out.println("running");
			Timer.delay(0.05);
			if(endAuto()) {break;}
		}
		Robot.drivebase.drive(0, 0);
		timer.stop();
	}
	
	public void driveForwardEncoder(double encoderTicks) {
		double encStart = Robot.drivebase.rDriveEnc.getDistance();
		while((encStart + encoderTicks) >= Robot.drivebase.rDriveEnc.getDistance()) {
			Robot.drivebase.drive(0.75, 0.75);
			Timer.delay(0.05);
			if(endAuto()) {break;}
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
			Timer.delay(0.05);
			if(endAuto()) {break;}
		}
	}
	
	public void turnDownForNavX(double degrees, double speed, boolean left, double intakePos) {
		double navXStart = Robot.drivebase.navX.getYaw();
		double initAngle = navXStart + degrees;
		double objectiveAngle = ((initAngle > -180) && (initAngle < 180)) ? initAngle : ((initAngle < 180) ? (initAngle + 360) : (initAngle - 360));
		double lSpeed;
		double rSpeed;
		
		if(left) {
			lSpeed = speed*-1;
			rSpeed = speed;
		}
		else {
			lSpeed = speed;
			rSpeed = speed*-1;
		}
		while(inRange((objectiveAngle + 2), (objectiveAngle - 2), Robot.drivebase.navX.getYaw())) {
			Robot.drivebase.drive(lSpeed, rSpeed);
			Robot.intake.intake.set(intakePos);
			Timer.delay(0.01);
			if(endAuto()) {break;}
		}
		Robot.drivebase.drive(0, 0);
	}

	public boolean endAuto() {
		return (Robot.autoTimer.get() > 14);
	}

	public void driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {
		Robot.drivebase.drive((powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant)));
	}

	public void driveStraightSeconds(double seconds, double powSetpoint, double tuningConstant, double intakePos) {
		double navXStart = Robot.drivebase.navX.getYaw();
		Timer timer = new Timer();
		timer.start();
		double timerStart = timer.get();
		while(!((timer.get() - timerStart) > seconds)) {
			driveStraightAngle(powSetpoint, (Robot.drivebase.navX.getYaw() - navXStart), tuningConstant);
			Robot.intake.intake.set(intakePos);
			Timer.delay(0.05);
			if(endAuto()) {break;}
		}
		Robot.drivebase.drive(0, 0);
		timer.stop();
	}
	
	public void driveStraightEncoder(double encoderTicks, double powSetpoint, double tuningConstant, double intakePos) {
		double navXStart = Robot.drivebase.navX.getYaw();
		double encoderStart = Robot.drivebase.rDriveEnc.getDistance();
		while(!((java.lang.Math.abs(Robot.drivebase.rDriveEnc.getDistance() - encoderStart)) > encoderTicks)) {
			driveStraightAngle(powSetpoint, (Robot.drivebase.navX.getYaw() - navXStart), tuningConstant);
			Robot.intake.intake.set(intakePos);
			Timer.delay(0.05);
			if(endAuto()) {break;}
		}
		Robot.drivebase.drive(0, 0);
	}
	
	public boolean inRange(double northBound, double southBound, double X) {
		return ((X > southBound) && (X < northBound));
	}
	
	
}
