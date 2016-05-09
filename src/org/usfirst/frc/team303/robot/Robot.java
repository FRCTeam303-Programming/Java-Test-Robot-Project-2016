
package org.usfirst.frc.team303.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team303.robot.Claw;
import org.usfirst.frc.team303.robot.Intake;
import org.usfirst.frc.team303.robot.RobotMap;
import org.usfirst.frc.team303.robot.Drivebase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    String autoSelected;
    SendableChooser chooser;
    static double clawSetpoint = 0;
    static double cntakeSetpoint = 0;
    static double clawWheelSetpoint = 0;
    
    /*
     * These objects may have to be moved to robotInit() or to Robot() constructor. 
     */
	static Claw claw = new Claw();
	static Intake intake = new Intake();
	static Drivebase drivebase = new Drivebase();
	static OI oi = new OI();
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        claw.clawInit(); //runs methods relating to configuring PID loops and encoders
        intake.intakeInit(); //runs methods relating to configuring PID loops and encoders
        oi.OIInit(); //runs methods relating to joystick creation
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	oi.updateJoyValues();                             //updates joystick values
        drivebase.drive(oi.lStickY, oi.rStickY);          //drive the robot based on inputs from OI
        clawSetpoint = claw.xboxClawCtrl(clawSetpoint);   //update the clawsetpoint
        claw.clawSet(clawSetpoint);	  //tell claw to go to setpoint
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        oi.updateJoyValues();
    }
    
}
