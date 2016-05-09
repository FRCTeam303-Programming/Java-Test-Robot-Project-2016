package org.usfirst.frc.team303.robot;

import org.usfirst.frc.team303.robot.Robot;
import org.usfirst.frc.team303.robot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Claw {
    static CANTalon claw;
    
	public Claw() {
		 claw = new CANTalon(RobotMap.CLAW);
	}

	public void clawInit() {
		claw.changeControlMode(CANTalon.TalonControlMode.Position);
		claw.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		claw.configEncoderCodesPerRev(360);
		claw.enableLimitSwitch(true, true);
		claw.setPID(1, 0, 0);
		claw.enableBrakeMode(false);
		claw.setSafetyEnabled(true);
		claw.enable();
		
	}
   
	public void clawSet(double setpoint) {
		claw.set(setpoint);
	}
	
	public void clawEncZero() {
		claw.setEncPosition(0);
	}

	public double xboxClawCtrl(double oldclaw) {
		if(Robot.oi.xboxBtnA)
			return 0;
		else if(Robot.oi.xboxBtnB)
			return 0;
		else if(Robot.oi.xboxBtnX)
			return 0;
		else if(Robot.oi.xboxBtnY)
			return 0;
		else if((Robot.oi.xboxLStickY)>0.25)
			return oldclaw + 0.05;
		else if((Robot.oi.xboxLStickY)<0.25)
			return oldclaw - 0.05;
		else return oldclaw;
	}
	
	
}

