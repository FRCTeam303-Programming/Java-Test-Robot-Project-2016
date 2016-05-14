package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.util.Arrays;

public class OI {
final int LSTICK_PORT = 0;
final int RSTICK_PORT = 1;
final int XBOX_PORT = 2;
Joystick lStick;
Joystick rStick;
Joystick xbox;
double lStickY;
double lStickX;
double rStickY;
double rStickX;
double xboxLStickY;
double xboxLTrigger;
double xboxRTrigger;
boolean lStickBtn1;
boolean lStickBtn2;
boolean lStickBtn3;
boolean lStickBtn4;
boolean lStickBtn5;
boolean lStickBtn6;
boolean lStickBtn7;
boolean rStickBtn1;
boolean rStickBtn2;
boolean rStickBtn3;
boolean rStickBtn4;
boolean rStickBtn5;
boolean rStickBtn6;
boolean rStickBtn7;
boolean xboxBtnA;
boolean xboxBtnB;
boolean xboxBtnX;
boolean xboxBtnY;
boolean xboxBtnStart;
boolean xboxBtnBack;
boolean xboxBtnLBumper;
boolean xboxBtnRBumper;
boolean xboxRStickPressed;

boolean[] lStickBtnArray;
boolean[] rStickBtnArray;
String lStickBtnArrayString;
String rStickBtnArrayString;


	public void OIInit() {
		 xbox = new Joystick(XBOX_PORT);
		 rStick = new Joystick(RSTICK_PORT);
		 lStick = new Joystick(LSTICK_PORT);
	}
	
	public void updateJoyValues() {
		lStickY = lStick.getRawAxis(1);
		lStickX = lStick.getRawAxis(0);
		lStickBtn1 = lStick.getRawButton(1);
		lStickBtn2 = lStick.getRawButton(2);
		lStickBtn3 = lStick.getRawButton(3);
		lStickBtn4 = lStick.getRawButton(4);
		lStickBtn5 = lStick.getRawButton(5);
		lStickBtn6 = lStick.getRawButton(6);
		lStickBtn7 = lStick.getRawButton(7);
		rStickY = (-1*(rStick.getRawAxis(1)));
		rStickX = rStick.getRawAxis(0);
		rStickBtn1 = rStick.getRawButton(1);
		rStickBtn2 = rStick.getRawButton(2);
		rStickBtn3 = rStick.getRawButton(3);
		rStickBtn4 = rStick.getRawButton(4);
		rStickBtn5 = rStick.getRawButton(5);
		rStickBtn6 = rStick.getRawButton(6);
		rStickBtn7 = rStick.getRawButton(7);
		xboxLStickY = xbox.getRawAxis(1);
		xboxRStickPressed = xbox.getRawButton(9);
		xboxBtnA = xbox.getRawButton(1);
		xboxBtnB = xbox.getRawButton(2);
		xboxBtnX = xbox.getRawButton(3);
		xboxBtnY = xbox.getRawButton(4);
		xboxBtnStart = xbox.getRawButton(8);
		xboxBtnBack = xbox.getRawButton(9);
		xboxBtnLBumper = xbox.getRawButton(5);
		xboxBtnRBumper = xbox.getRawButton(6);
		xboxLTrigger = xbox.getRawAxis(2);
		xboxRTrigger = xbox.getRawAxis(3);
		
		for(int i=1;i<8;i++)
		{
			lStickBtnArray[i] = stickBtnReturn(true, i);
		}
		
		for (int i=1;i<8;i++)
		{
			rStickBtnArray[i] = stickBtnReturn(false, i);
		}
		
		lStickBtnArrayString = Arrays.toString(lStickBtnArray);
		SmartDashboard.putString("lStickBtnArray", lStickBtnArrayString);
		
		rStickBtnArrayString = Arrays.toString(rStickBtnArray);
		SmartDashboard.putString("rStickBtnArray", rStickBtnArrayString);
		
	}
	
	/**
	 * Will return the joystick button value of the number specified. if l==true use left joystick, if l==false use right.
	 */
	public boolean stickBtnReturn(boolean l, int j) {
		if(l) {
			switch(j) {
			case 1 : return lStickBtn1;
			case 2 : return lStickBtn2;
			case 3 : return lStickBtn3;
			case 4 : return lStickBtn4;
			case 5 : return lStickBtn5;
			case 6 : return lStickBtn6;
			case 7 : return lStickBtn7;
			default : return false;
			}
		}
		else {
			switch(j) {
			case 1 : return rStickBtn1;
			case 2 : return rStickBtn2;
			case 3 : return rStickBtn3;
			case 4 : return rStickBtn4;
			case 5 : return rStickBtn5;
			case 6 : return rStickBtn6;
			case 7 : return rStickBtn7;
			default : return false;
			}
		}
	}
	
}
