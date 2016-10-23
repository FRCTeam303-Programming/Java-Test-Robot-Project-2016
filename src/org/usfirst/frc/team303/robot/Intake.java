package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class Intake {
	CANTalon intake;
	double P = 1.5;
	double I = 0;
	double D = 0.1;
	double ballSetpoint = 0.56;
	double verticalSetpoint = 2.0;
	
	public Intake() {
		intake = new CANTalon(RobotMap.INTAKE);
	}
	
	public void intakeInit() {
		intake.changeControlMode(CANTalon.TalonControlMode.Position);
		intake.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		intake.configEncoderCodesPerRev(360);
		//playoffs!?!?! PLAYOFFS?!?! DONT YOU BE TALKING TO ME ABOUE PLAOFFS!!!!
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
	
	public double forceDown(double setpoint, double magnitude) {
		if(Robot.oi.rStickBtn5) {
			return setpoint - magnitude;
		}
		else if(Robot.oi.lStickBtn4) {
			return ballSetpoint;
		}
		else if(Robot.oi.rStickBtn4) {
			return verticalSetpoint;
		}
		else {return setpoint;}
	}
	
	public double forceUp(double setpoint, double magnitude) {
		if(Robot.oi.rStickBtn3) {
			return setpoint + magnitude;
		}
		else if(Robot.oi.lStickBtn4) {
			return ballSetpoint;
		}
		else if(Robot.oi.rStickBtn4) {
			return verticalSetpoint;
		}
		else {return setpoint;}
	}
	
	public double intakeCtrl(double setpoint, double magnitude) {
	
		if(setpoint<2.4 && setpoint>-0.1) {
			if(Robot.oi.rStickBtn5) {
				return setpoint - magnitude;
			}
			else if(Robot.oi.rStickBtn3) {
				return setpoint + magnitude;
			}
			else if(Robot.oi.lStickBtn4) {
				return ballSetpoint;
			}
			else if(Robot.oi.rStickBtn4) {
				return verticalSetpoint;
			}
			else {return setpoint;}
		}
		else if((setpoint>=2.4)){
			return forceDown(setpoint, magnitude);
		}
		else if(setpoint<-0.1) {
			return forceUp(setpoint, magnitude);
		}
		else {return setpoint;}
		
	}
	
}
