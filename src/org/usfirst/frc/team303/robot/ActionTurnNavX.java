package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class ActionTurnNavX implements Action {
	double angleSetpoint;
	boolean firstRun=true;
	double kp;
	double ki;
	double kd;
	double errorSum=0;
	double previousError;
	double previousTime=0;
	double doneCounter;
	Timer t= new Timer();
	public ActionTurnNavX(double angleC,double kpC,double kiC,double kdC){
		angleSetpoint=angleC;
		kp=kpC;
		ki=kiC;
		kd=kdC;
		firstRun=true;
	}
	public boolean isFinished(){
		if(Math.abs(angleSetpoint-Robot.drivebase.navX.getYaw())<=2){
			doneCounter++;
		}
		else{
			doneCounter=0;
		}
		return(doneCounter>=5);
	}
	public void run(){
		if(firstRun){
			Robot.drivebase.navX.zeroYaw();
			t.start();
		}
		double error=angleSetpoint-Robot.drivebase.navX.getYaw();
		double p= error*kp;
		errorSum+=error*(t.get()-previousTime);
		double i=errorSum*ki;
		double d;
		if(firstRun){
			d=0;
			firstRun=false;
		}
		else{
		d=(error-previousError)/(t.get()-previousTime)*kd;
		}
		double speed= p+i+d;
		Robot.drivebase.drive(-speed, -speed);
		SmartDashboard.putNumber("Time", t.get()-previousTime);
		previousTime=t.get();
		SmartDashboard.putNumber("ERROR IN NAVX", error);
		SmartDashboard.putNumber("t1", angleSetpoint);
		SmartDashboard.putNumber("t2", Robot.drivebase.navX.getYaw());
		SmartDashboard.putNumber("The D", d);
		SmartDashboard.putNumber("The I", i);
		SmartDashboard.putNumber("The P", p);
		SmartDashboard.putNumber("The PID", speed);
		
	}
}
