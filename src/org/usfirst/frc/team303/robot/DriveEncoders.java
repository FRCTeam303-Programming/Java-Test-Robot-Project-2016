package org.usfirst.frc.team303.robot;
import org.usfirst.frc.team303.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveEncoders implements Action{
	double encoders;
	double power;
	double encoderStart;
	double navXStart;
	double tuningConstant;
	boolean firstRun;
	public DriveEncoders(double encodersC, double powerC,double tuningConstantC){
		encoders=encodersC;
		power=powerC;
		tuningConstant=tuningConstantC;
		firstRun=true;
	}
	public boolean isFinished(){
		if(firstRun)
			return false;
		return Math.abs(((Robot.drivebase.rDriveEnc.getDistance()+Robot.drivebase.lDriveEnc.getDistance())/2)-(encoderStart))>=encoders;
	}
	public void run(){
		if(firstRun){
			encoderStart = (Robot.drivebase.rDriveEnc.getDistance()+Robot.drivebase.lDriveEnc.getDistance())/2;
			navXStart = (Robot.drivebase.navX.getYaw());
			Robot.drivebase.drive(-1*(power + ((Robot.drivebase.navX.getYaw() - navXStart)*tuningConstant)), (power - ((Robot.drivebase.navX.getYaw() - navXStart)*tuningConstant)));
			firstRun = false;
		}
		else {
		
			double error = Robot.drivebase.navX.getYaw() - navXStart;
			Robot.drivebase.drive(-1*(power+(error*tuningConstant)),power-(error*tuningConstant));
			SmartDashboard.putNumber("ERROR IN NAVX", error);
			
		}
	}
	public void autoDrive(int speed){
		
	}
}
