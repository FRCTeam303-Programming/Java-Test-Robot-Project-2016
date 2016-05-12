package org.usfirst.frc.team303.robot;

import org.usfirst.frc.team303.robot.Robot;
import org.usfirst.frc.team303.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Claw {
    static CANTalon claw;
    double P = 10;
    double I = 0.0004;
    double D = 0.1;
   
	public Claw() {
		 claw = new CANTalon(RobotMap.CLAW);
	}

	public void clawInit() {
		claw.changeControlMode(CANTalon.TalonControlMode.Position);
		claw.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		claw.configEncoderCodesPerRev(360);
		claw.enableLimitSwitch(false, false);
		claw.setPID(P, I, D);
		claw.enableBrakeMode(false);
		claw.setSafetyEnabled(true);
		claw.enable();
		Robot.clawSetpoint = 0;
		
	}
   
	public void clawSet(double setpoint) {
		claw.set(setpoint);
	}

	public double xboxClawCtrl(double oldclaw) {
		if(Robot.oi.xboxBtnA)
			return -0.015;
		else if(Robot.oi.xboxBtnB)
			return -0.0685;
		else if(Robot.oi.xboxBtnX)
			return -0.06;
		else if(Robot.oi.xboxBtnY)
			return oldclaw;
		else if((Robot.oi.xboxLStickY)>0.75)
			return oldclaw + 0.005;
		else if((Robot.oi.xboxLStickY)<-0.75)
			return oldclaw - 0.005;
		else return oldclaw;
	}
	
	public double clawGetCheck() {
		double clawPos = claw.getPosition();
		SmartDashboard.putNumber("Claw Rotation", clawPos);
		SmartDashboard.putBoolean("FwdLimit", claw.isFwdLimitSwitchClosed());
		if(claw.isFwdLimitSwitchClosed())	//TODO move this to a different method
			claw.setEncPosition(0);
		
		return clawPos;
		
	}
	
}

