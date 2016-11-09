package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

public class Pneumatics {

	Compressor compressor = new Compressor();
	Solenoid piston = new Solenoid(0);
	Solenoid gearShift = new Solenoid(1);
	
	
	public Pneumatics() {
		
	}
	
	public void pneumaticsInit() {
		compressor.start();
	}
	
	public void pneumaticsCtrl(boolean pistonS, boolean gearShiftS) {
		piston.set(pistonS);
		gearShift.set(gearShiftS);
	}
}
