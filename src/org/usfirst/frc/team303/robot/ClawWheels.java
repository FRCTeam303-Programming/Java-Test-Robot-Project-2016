package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawWheels {
   CANTalon clawWheelL;
   CANTalon clawWheelR;
   double LP = 0.2;
   double LI = 0.0000; //0.001
   double LD = 0.00;  //0.75
   
   double RP = 0.2;
   double RI = 0.00000; //0.001
   double RD = 0.00;  //0.75
   double speedL = 0;
   double speedR = 0;
   
   public ClawWheels() {
	   clawWheelL = new CANTalon(RobotMap.LCLAWWHEEL);
	   clawWheelR = new CANTalon(RobotMap.RCLAWWHEEL);
   }
   
   public void clawWheelsInit() {
	   clawWheelL.changeControlMode(CANTalon.TalonControlMode.Speed);
	   clawWheelL.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	   clawWheelL.configEncoderCodesPerRev(360);
	   clawWheelL.setPID(LP, LI, LD);
	   clawWheelL.enableBrakeMode(false);
	   clawWheelL.setSafetyEnabled(true);
	   clawWheelL.enable();
	   
	   clawWheelR.changeControlMode(CANTalon.TalonControlMode.Speed);
	   clawWheelR.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	   clawWheelR.configEncoderCodesPerRev(360);
	   clawWheelR.setPID(RP, RI, RD);
	   clawWheelR.enableBrakeMode(false);
	   clawWheelR.setSafetyEnabled(true);
	   clawWheelR.enable();
   }
   
   public double xboxWheelCtrl(double oldwheels) {
	   if(Robot.oi.xboxBtnA)
			return 0;
		else if(Robot.oi.xboxBtnB)
			return 5000;
		else if(Robot.oi.xboxBtnX)
			return 20000;
		else if(Robot.oi.xboxBtnY)
			return 0;
		else return oldwheels;
   }
   
   public double realClawWheelsCtrl(double oldwheels, double clawRotation, double clawSetpoint) {
	   double wheelSetpoint = oldwheels;
	   
	   speedL = clawWheelL.getSpeed();
	   speedR = clawWheelR.getSpeed();
	   SmartDashboard.putNumber("L Speed", speedL);
	   SmartDashboard.putNumber("R Speed", speedR);
	   
	   if((Robot.oi.lStickBtn3 || Robot.oi.lStickBtn2)) {
		   if(Robot.oi.lStickBtn3 ^ Robot.oi.lStickBtn2) {
			   if(Robot.oi.lStickBtn2)
				   return -4000;
			   else if(Robot.oi.lStickBtn3)
				   return 4000;
			   else {return 0;} //never reached
		   }
		   else {return 0;} //never reached
	   }
	   else {
		   if(((clawRotation - 0.05)<=clawSetpoint) && ((clawRotation + 0.05)>=clawSetpoint)) {
			   SmartDashboard.putBoolean("test", true);
			   if(clawRotation<-0.06) {
				   return wheelSetpoint;
			   }
			   else {return 0;} //default case?
		   }
		   else {
			   SmartDashboard.putBoolean("test", false);
			   return -2000;
		   }
	   }
	   
   }
   
   public void clawWheelsSet(double setpoint) {
	   clawWheelL.set(setpoint);
	   clawWheelR.set(-1*(setpoint));
   }
}
