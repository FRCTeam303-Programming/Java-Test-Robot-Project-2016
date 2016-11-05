package org.usfirst.frc.team303.robot;
import org.usfirst.frc.team303.robot.Robot;

public class DriveEncoders implements Action{
	double encoders;
	double power;
	double encoderStart;
	double navXStart;
	double tuningConstant;
	boolean firstRun=true;
	public DriveEncoders(double encodersC, double powerC,double tuningConstantC){
		encoders=encodersC;
		power=powerC;
		tuningConstant=tuningConstantC;
		
	}
	public boolean isFinished(){
		if(firstRun)
			return false;
		return Math.abs(((Robot.drivebase.rDriveEnc.getDistance()+Robot.drivebase.lDriveEnc.getDistance())/2)-(encoderStart))<=10;
	}
	public void run(){
		if(firstRun){
			encoderStart = (Robot.drivebase.rDriveEnc.getDistance()+Robot.drivebase.lDriveEnc.getDistance())/2;
			navXStart = Robot.drivebase.navX.getYaw();
			Robot.drivebase.drive((power + ((Robot.drivebase.navX.getYaw() - navXStart)*tuningConstant)), (power - ((Robot.drivebase.navX.getYaw() - navXStart)*tuningConstant)));
		}
		
	}
	public void autoDrive(int speed){
		
	}
}
