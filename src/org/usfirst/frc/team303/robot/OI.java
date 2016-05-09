package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
final int LSTICK_PORT = 0;
final int RSTICK_PORT = 1;
final int XBOX_PORT = 2;
Joystick lStick;
Joystick rStick;
Joystick xbox;
double lStickY;
double rStickY;
double xboxLStickY;
double xboxLTrigger;
double xboxRTrigger;
boolean lStickBtn0;
boolean lStickBtn1;
boolean lStickBtn2;
boolean lStickBtn3;
boolean lStickBtn4;
boolean lStickBtn5;
boolean lStickBtn6;
boolean lStickBtn7;
boolean lStickBtn8;
boolean rStickBtn0;
boolean rStickBtn1;
boolean rStickBtn2;
boolean rStickBtn3;
boolean rStickBtn4;
boolean rStickBtn5;
boolean rStickBtn6;
boolean rStickBtn7;
boolean rStickBtn8;
boolean xboxBtnA;
boolean xboxBtnB;
boolean xboxBtnX;
boolean xboxBtnY;
boolean xboxBtnStart;
boolean xboxBtnBack;
boolean xboxBtnLBumper;
boolean xboxBtnRBumper;
boolean xboxRStickPressed;


	public void OIInit() {
		 xbox = new Joystick(XBOX_PORT);
		 rStick = new Joystick(RSTICK_PORT);
		 lStick = new Joystick(RSTICK_PORT);
	}
	
	public void updateJoyValues() {
		lStickY = lStick.getRawAxis(1);
		lStickBtn0 = lStick.getRawButton(0);
		lStickBtn1 = lStick.getRawButton(1);
		lStickBtn2 = lStick.getRawButton(2);
		lStickBtn3 = lStick.getRawButton(3);
		lStickBtn4 = lStick.getRawButton(4);
		lStickBtn5 = lStick.getRawButton(5);
		lStickBtn6 = lStick.getRawButton(6);
		lStickBtn7 = lStick.getRawButton(7);
		lStickBtn8 = lStick.getRawButton(8);
		rStickY = rStick.getRawAxis(1);
		rStickBtn0 = rStick.getRawButton(0);
		rStickBtn1 = rStick.getRawButton(1);
		rStickBtn2 = rStick.getRawButton(2);
		rStickBtn3 = rStick.getRawButton(3);
		rStickBtn4 = rStick.getRawButton(4);
		rStickBtn5 = rStick.getRawButton(5);
		rStickBtn6 = rStick.getRawButton(6);
		rStickBtn7 = rStick.getRawButton(7);
		rStickBtn8 = rStick.getRawButton(8);
		xboxLStickY = xbox.getRawAxis(1);
		xboxRStickPressed = xbox.getRawButton(9);
		xboxBtnA = xbox.getRawButton(0);
		xboxBtnB = xbox.getRawButton(1);
		xboxBtnX = xbox.getRawButton(2);
		xboxBtnY = xbox.getRawButton(3);
		xboxBtnStart = xbox.getRawButton(7);
		xboxBtnBack = xbox.getRawButton(6);
		xboxBtnLBumper = xbox.getRawButton(4);
		xboxBtnRBumper = xbox.getRawButton(5);
		xboxLTrigger = xbox.getRawAxis(2);
		xboxRTrigger = xbox.getRawAxis(3);
	}
	
}
