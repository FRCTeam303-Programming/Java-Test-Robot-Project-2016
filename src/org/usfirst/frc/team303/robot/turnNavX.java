package org.usfirst.frc.team303.robot;
public class turnNavX implements Action {
	double angleSetpoint;
	boolean firstRun=true;
	double kp;
	double ki;
	double kd;
	double errorSum=0;
	double previousError;
	public turnNavX(double angleC,double kpC,double kiC,double kdC){
		angleSetpoint=angleC;
	}
	public boolean isFinished(){
		return(Math.abs(angleSetpoint-Robot.drivebase.navX.getYaw()))<=2;
	}
	public void run(){
		if(firstRun){
			if(Math.abs(Robot.drivebase.navX.getYaw()-angleSetpoint)>180){
				if(Robot.drivebase.navX.getYaw()>angleSetpoint)
					angleSetpoint+=360;
				else
					angleSetpoint-=360;
			}
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
		Robot.drivebase.drive(speed, -speed);
		
	}
}
