package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Utility;

public class Intake {
	CANTalon intake;
	double P = 1.5;
	double I = 0;
	double D = 0.1;
	
	public Intake() {
		intake = new CANTalon(RobotMap.INTAKE);
	}
	
	public void intakeInit() {
		intake.changeControlMode(CANTalon.TalonControlMode.Position);
		intake.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		intake.configEncoderCodesPerRev(360);
		intake.enableLimitSwitch(true, true);
		intake.setPID(P, I, D);
		intake.enableBrakeMode(false);
		intake.setSafetyEnabled(true);
		intake.enable();
		Robot.intakeSetpoint = 0;
	}
	
	public void intakeSet(double setpoint) {
		intake.set(setpoint);
	}
	
	public void intakeEncZero() {
		intake.setEncPosition(0);
	}
	
	
	public double intakeCtrl(double setpoint, double magnitude) {
		if(setpoint>2.5) { //TODO retest this constant
			return 2.5;
		}
		else if(setpoint<-0.2) {
			return -0.2;
		}
		else if(Robot.oi.rStickBtn5) {
			return setpoint - magnitude;
		}
		else if(Robot.oi.rStickBtn3) {
			return setpoint + magnitude;
		}
		else if(Robot.oi.lStickBtn4) {
			return 0.56;
		}
		else if(Robot.oi.rStickBtn4) {
			return 2.0;
		}
		else {return setpoint;}
	}
}
