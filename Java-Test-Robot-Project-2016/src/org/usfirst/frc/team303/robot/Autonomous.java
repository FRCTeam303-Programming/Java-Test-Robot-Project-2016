package org.usfirst.frc.team303.robot;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous {

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
	}
	
	
	//MAIN METHODS BELOW
	public void lowBarAuto() { //0.6
		putClawDown();
		Robot.pneumatics.gearShift.set(false);
	}
	
	public void rockWallAuto() {
		Robot.pneumatics.gearShift.set(true);
		Robot.intake.intakeSet(1.0);
		driveForwardSeconds(4000);
	}
	
	public void featureTest() {
		putClawDown();
		driveStraightEncoder(250, 0.7, 0.01, -0.15); //encoderTicks, powSetpoint, tuningConstant, intakePos
		turnDownForNavX(90, 0.65, true, -0.15, Robot.autoInitialNavX); //degrees, speed, left?, intakePos
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	// HELPER METHODS BELOW
	public void driveForwardSeconds(double seconds) {
		if((Robot.autoCount) <= (seconds/20)) {
			Robot.drivebase.drive(-0.80, 0.80);
		}
		else {
			Robot.drivebase.drive(0, 0);
		}
		Robot.autoCount++;
	}
	
	public void driveForwardEncoder(double encoderTicks) {
		double encStart = Robot.drivebase.rDriveEnc.getDistance();
		if((encStart + encoderTicks) >= Robot.drivebase.rDriveEnc.getDistance()) {
			Robot.drivebase.drive(0.75, 0.75);
			Timer.delay(0.05);
		}
		Robot.drivebase.drive(0, 0);
	}
	
	@SuppressWarnings("static-access")
	public void putClawDown() {
		double clawStart = Robot.claw.claw.getPosition(); //this method complains about static access
		double acc = 0;
		if(!Robot.claw.claw.isFwdLimitSwitchClosed()) { //this method complains about static access
			Robot.claw.clawSet(clawStart + acc);
			acc = acc + 0.0001;
		}
	}
	
	public void turnDownForNavX(double degrees, double speed, boolean left, double intakePos, double navXStart) {
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
		if(inRange((objectiveAngle + 2), (objectiveAngle - 2), Robot.drivebase.navX.getYaw())) {
			Robot.drivebase.drive(lSpeed, rSpeed);
			Robot.intake.intake.set(intakePos);
		}
		else {
			Robot.drivebase.drive(0, 0);
		}
		
	}

	public void driveStraightAngle(double powSetpoint, double angleDifference, double tuningConstant) {
		Robot.drivebase.drive((powSetpoint + (angleDifference*tuningConstant)), (powSetpoint - (angleDifference*tuningConstant)));
	}

	public void driveStraightSeconds(double seconds, double powSetpoint, double tuningConstant, double intakePos, double navXStart) {

		if(Robot.autoCount <=  (seconds/20)) {
			driveStraightAngle(powSetpoint, (Robot.drivebase.navX.getYaw() - navXStart), tuningConstant);
			Robot.intake.intake.set(intakePos);
		}
		else {
			Robot.drivebase.drive(0, 0);
		}
		Robot.autoCount++;
	}
	
	public void driveStraightEncoder(double encoderTicks, double powSetpoint, double tuningConstant, double intakePos) {
		double navXStart = Robot.drivebase.navX.getYaw();
		double encoderStart = Robot.drivebase.rDriveEnc.getDistance();
		if(!((java.lang.Math.abs(Robot.drivebase.rDriveEnc.getDistance() - encoderStart)) > encoderTicks)) {
			driveStraightAngle(powSetpoint, (Robot.drivebase.navX.getYaw() - navXStart), tuningConstant);
			Robot.intake.intake.set(intakePos);
			Timer.delay(0.05);
		}
		else {
			Robot.drivebase.drive(0, 0);	
		}
	}
	
	public boolean inRange(double northBound, double southBound, double X) {
		return ((X > southBound) && (X < northBound));
	}
	
	
}
