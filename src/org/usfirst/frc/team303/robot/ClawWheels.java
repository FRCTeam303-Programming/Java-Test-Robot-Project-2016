package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.CANTalon;

public class ClawWheels {
   CANTalon clawWheelL;
   CANTalon clawWheelR;
   double P = 0.1;
   double I = 0.001;
   double D = 0.75;
   
   public ClawWheels() {
	   clawWheelL = new CANTalon(RobotMap.LCLAWWHEEL);
	   clawWheelR = new CANTalon(RobotMap.RCLAWWHEEL);
   }
   
   public void ClawWheelsInit() {
	   clawWheelL.changeControlMode(CANTalon.TalonControlMode.Speed);
	   clawWheelL.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	   clawWheelL.configEncoderCodesPerRev(360);
	   clawWheelL.enableLimitSwitch(true, true);
	   clawWheelL.setPID(P, I, D);
	   clawWheelL.enableBrakeMode(false);
	   clawWheelL.setSafetyEnabled(true);
	   clawWheelL.enable();
	   clawWheelR.changeControlMode(CANTalon.TalonControlMode.Speed);
	   clawWheelR.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	   clawWheelR.configEncoderCodesPerRev(360);
	   clawWheelR.enableLimitSwitch(true, true);
	   clawWheelR.setPID(P, I, D);
	   clawWheelR.enableBrakeMode(false);
	   clawWheelR.setSafetyEnabled(true);
	   clawWheelR.enable();
   }
   
   public double xboxWheelCtrl(double oldwheels) {
	   if(Robot.oi.xboxBtnA)
			return 0;
		else if(Robot.oi.xboxBtnB)
			return 1800;
		else if(Robot.oi.xboxBtnX)
			return 2000;
		else if(Robot.oi.xboxBtnY)
			return 0;
		else return oldwheels;
   }
   
   public double realClawWheelsCtrl(double oldwheels, double clawRotation, double clawSetpoint) {
	   double wheelSetpoint = xboxWheelCtrl(oldwheels);
	   
	   if((wheelSetpoint==0) && (Robot.oi.lStickBtn4 || Robot.oi.lStickBtn2)) {
		   if(Robot.oi.lStickBtn4 ^ Robot.oi.lStickBtn2) {
			   if(Robot.oi.lStickBtn4)
				   return 2000;
			   else if(Robot.oi.lStickBtn2)
				   return -2000;
			   else return 0;
		   }
		   else return 0;
	   }
	   else {
		   if((clawRotation - 0.0075<=clawSetpoint) && (clawRotation + 0.0075>=clawSetpoint)) {
			   return wheelSetpoint;
		   }
		   else return 500;
	   }
   }
   
   public void clawWheelsSet(double setpoint) {
	   clawWheelL.set(setpoint);
	   clawWheelR.set(setpoint);
   }
}
