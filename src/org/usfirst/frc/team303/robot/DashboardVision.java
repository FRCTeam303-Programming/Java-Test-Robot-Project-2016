package org.usfirst.frc.team303.robot;

public class DashboardVision {
	static double navXSetpoint;
	static double encoderSetpoint;
	
	public DashboardVision() {
		//is there any code that needs to run once?
	}
	
	//this method is complete
	public double[] calculateSetpoints(int rectLeft, int rectRight, int rectTop, int rectBottom) {
		int xIdealPixel = 150;
		int yIdealPixel = 26;	
		navXSetpoint = (((xIdealPixel-((rectLeft+rectRight)/2))*-0.18659375)+Robot.drivebase.navX.getYaw());
		encoderSetpoint = (((yIdealPixel-((rectTop+rectBottom)/2))*0.09)+Robot.drivebase.lDriveEnc.getDistance());
		double[] visionValues = {navXSetpoint, encoderSetpoint};
		return visionValues;
	}
	
	public double[] navXVisionSub() {
		double[] leftRight = {0, 0};
		double pidOutput = 0;
		
		double currentEncoder = -1*(Robot.drivebase.lDriveEnc.getDistance());
		double currentNavX = Robot.drivebase.navX.getYaw();
		
		double xPidP = 0.035;
		double xPidI = 0.005;
		double xPidD = 0; //this is supposed to be zero
		
		double xNumFeed = navXSetpoint - currentNavX;
		double yNumFeed = encoderSetpoint - currentEncoder;
		boolean xBoolFeed = inRange(navXSetpoint, currentNavX, navXSetpoint, 0.5);
		boolean yBoolFeed = inRange(encoderSetpoint, currentEncoder, encoderSetpoint, 20);
		boolean yGreaterThanSetpoint = (currentEncoder>encoderSetpoint);
		boolean yLessThanSetpoint = (currentEncoder<encoderSetpoint);
		
		if(xBoolFeed && yBoolFeed) {
			//code for pidOutput will go here
			
			
			//this stuff goes at the end of the X pid calculation
			leftRight = arcadeMap(pidOutput, 1, 0);
			leftRight = rectifyArcadeDrive(leftRight[0], leftRight[1]);
			return leftRight;
		}
		else {
			//this ELSE STATEMENT is complete
			double[] zero = {0, 0};
			return zero;
		}
		
	}
	
	//this method is complete
	public boolean inRange(double top, double x, double bottom, double range) {
		return (((bottom - range)<=x) && ((top + range)>=x));
	}
	
	//this method is complete
	public double[] arcadeMap(double xValue, double maxOutput, double yValue) {
		double[] leftRight = {0, 0};
		if(xValue>0) {
			 double leftMotor = maxOutput*(returnMaximum(-1*yValue, xValue));
			 double rightMotor = maxOutput*(xValue + yValue);
			 leftRight[0] = leftMotor;
			 leftRight[1] = rightMotor;
		}
		else {
			double leftMotor = maxOutput*(yValue-xValue);
			double rightMotor = maxOutput*(returnMaximum(-1*yValue, -1*xValue));
			leftRight[0] = leftMotor;
			leftRight[1] = rightMotor;
		}
		return leftRight;
	}
	
	//this method is complete
	public double returnMaximum(double z, double y) {
		if(z>=y) {
			return -1*z;
		}
		else {
			return -1*y;
		}
	}

	//this method is complete
	public double[] rectifyArcadeDrive(double leftMotor, double rightMotor) {
		double[] leftRight = {0, 0};
		leftRight[0] = (leftMotor>1) ? 1 : leftMotor;
		leftRight[1] = (rightMotor>1) ? 1 : rightMotor;
		return leftRight;
	}
	
}
