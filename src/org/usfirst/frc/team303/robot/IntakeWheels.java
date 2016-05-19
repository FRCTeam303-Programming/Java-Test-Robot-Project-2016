package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class IntakeWheels {
CANTalon intakeWheels;
	
	public IntakeWheels() {
		intakeWheels = new CANTalon(RobotMap.INTAKEWHEELS);
	}
	
	public void intakeWheelsInit() {
		intakeWheels.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		intakeWheels.setInverted(RobotMap.INTAKEWHEELS_INV);
		intakeWheels.setSafetyEnabled(true);
	}
	
	public double intakeWheelsCtrl() {
		if((Robot.oi.lStickBtn5 ||Robot.oi.lStickBtn3||Robot.oi.lStickBtn2)&&!(Robot.oi.lStickBtn5 &&Robot.oi.lStickBtn3)&&!(Robot.oi.lStickBtn5 &&Robot.oi.lStickBtn2)&&!(Robot.oi.lStickBtn3 &&Robot.oi.lStickBtn2)) {
			if(Robot.oi.lStickBtn3)
				return 1;
			else if (Robot.oi.lStickBtn5 || Robot.oi.lStickBtn2)
				return -1;
			else return 0;
		}
			else return 0;
		}
			
	
	public void set(double percentVoltage) {
		intakeWheels.set(percentVoltage);
	}
}
