package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.CANTalon;

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
	}
	
	public void intakeSet(double setpoint) {
		intake.set(setpoint);
	}
	
	public void intakeEncZero() {
		intake.setEncPosition(0);
	}
}
