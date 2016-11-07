package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnNavX implements Action {
	double angleSetpoint;
	boolean firstRun=true;
	double kp;
	double ki;
	double kd;
	double errorSum=0;
	double previousError;
	
	public TurnNavX(double angleC,double kpC,double kiC,double kdC){
		angleSetpoint=angleC;
	}
	public boolean isFinished(){
		return(Math.abs(angleSetpoint-Robot.drivebase.navX.getYaw()))<=2;
	}
	public void run(){
		if(firstRun){
			Robot.drivebase.navX.zeroYaw();
		}
		double error=angleSetpoint-Robot.drivebase.navX.getYaw();
		double p= error*kp;
		errorSum+=error;
		double i=errorSum*ki;
		double d;
		if(firstRun){
			d=0;
			firstRun=false;
		}
		else{
		d=error-previousError;
		}
		double speed= p+i+d;
		Robot.drivebase.drive(speed, speed);
		SmartDashboard.putNumber("ERROR IN NAVX", error);
		SmartDashboard.putNumber("t1", angleSetpoint);
		SmartDashboard.putNumber("t2", Robot.drivebase.navX.getYaw());
	}
}
