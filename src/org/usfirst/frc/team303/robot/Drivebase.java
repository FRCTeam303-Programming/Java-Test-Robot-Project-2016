package org.usfirst.frc.team303.robot;

import org.usfirst.frc.team303.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;

public class Drivebase {
	CANTalon FL;
	CANTalon FR;
	CANTalon BL;
	CANTalon BR;
	RobotDrive drivebase;
	Encoder lDriveEnc;
	Encoder rDriveEnc;
	
	public Drivebase() {
		FL = new CANTalon(RobotMap.FL);
		FR = new CANTalon(RobotMap.FR);
		BL = new CANTalon(RobotMap.BL);
		BR = new CANTalon(RobotMap.BR);
		drivebase = new RobotDrive(FL, BL, FR, BR);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, RobotMap.FL_INV);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kFrontRight, RobotMap.FR_INV);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kRearLeft, RobotMap.BL_INV);
		drivebase.setInvertedMotor(RobotDrive.MotorType.kRearRight, RobotMap.BR_INV);
		drivebase.setSafetyEnabled(true);
		lDriveEnc = new Encoder(0, 1);
		rDriveEnc = new Encoder(2, 3);
	}
	
	public void drive(double left, double right) {
		drivebase.tankDrive(left, right);
	}
	
}
