package org.usfirst.frc.team303.robot;
import org.usfirst.frc.team303.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ActionDriveEncoders implements Action{
	double encoders;
	double power;
	double encoderStart;
	double navXStart;
	double tuningConstant;
	boolean firstRun;
	public ActionDriveEncoders(double encodersC, double powerC,double tuningConstantC){
		encoders=encodersC;//how many encoder ticks
		power=powerC;//how fast
		tuningConstant=tuningConstantC;//-.01 works well
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
			Robot.drivebase.navX.zeroYaw();
			firstRun = false;
		}
		else {
		
			double error = Robot.drivebase.navX.getYaw();
			Robot.drivebase.drive(-1*(power+(error*tuningConstant)),power-(error*tuningConstant));
			
		}
	}
}
