package org.usfirst.frc.team303.robot;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Utility;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team303.robot.Claw;
import org.usfirst.frc.team303.robot.Intake;
import org.usfirst.frc.team303.robot.RobotMap;
import org.usfirst.frc.team303.robot.Drivebase;
import edu.wpi.first.wpilibj.networktables.*;
 
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
@SuppressWarnings("unused")
public class Robot extends IterativeRobot {
    final static String defaultAuto = "Default", lowBar = "Low Bar", rockWall = "Rock Wall / Rough Terrain", featureTest = "Feature Test";
    static String autoSelected1;
    static SendableChooser chooser1, chooser2;
    static double clawSetpoint = 0, intakeSetpoint = 0, clawWheelSetpoint = 0, clawRotation = 0;
    static double[] visionMotors = {0, 0};
    
    static double autoInitialNavX;
    static int autoCount = 0;
    
    static int rectLeft = 0, rectRight = 0, rectTop = 0, rectBottom = 0;
    static double[] visionSetpoints = {0, 0};
    
    /*
     * These objects may have to be moved to robotInit() or to Robot() constructor. 
     */
	static Claw claw = new Claw();
	static Intake intake = new Intake();
	static Drivebase drivebase = new Drivebase();
	static OI oi = new OI();
	static ClawWheels clawwheels = new ClawWheels();
	static IntakeWheels intakewheels = new IntakeWheels();
	static Pneumatics pneumatics = new Pneumatics();
	static DashboardVision vision = new DashboardVision();
	static Auto auto;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser1 = new SendableChooser();
        chooser1.addDefault("Default Auto", defaultAuto);
        chooser1.addObject("Low Bar", lowBar);
        chooser1.addObject("Rock Wall / Rough Terrain", rockWall);
        chooser1.addObject("Feature Test", featureTest);
        SmartDashboard.putData("Auto choices", chooser1);
        
        drivebase.drivebaseInit(); //runs methods relating to configuring motor direction and encoders
        claw.clawInit(); //runs methods relating to configuring PID loops and encoders
        intake.intakeInit(); //runs methods relating to configuring PID loops and encoders
        oi.OIInit(); //runs methods relating to joystick creation
        clawwheels.clawWheelsInit();
        intakewheels.intakeWheelsInit();
        pneumatics.pneumaticsInit();
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
    	autoSelected1 = (String) chooser1.getSelected();
		System.out.println("Auto selected: " + autoSelected1);
		autoInitialNavX = drivebase.navX.getYaw();
		auto = new Auto(autoSelected1);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	drivebase.updateSensors();
    	claw.clawGetCheck();
    	auto.run();
    }

    
    public void teleopInit() {
    	clawSetpoint = 0;
    	autoCount = 0;
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	oi.updateJoyValues();                             //updates joystick values
    	drivebase.updateSensors();                        //updates drive encoders and navX
    	
    	/*
    	if(oi.xboxBtnBack) { //vision control part 1
    		rectTop = (int) SmartDashboard.getNumber("/rectangle/TOP");
    		rectBottom = (int) SmartDashboard.getNumber("/rectangle/BOTTOM");
    		rectLeft = (int) SmartDashboard.getNumber("/rectangle/LEFT");
    		rectRight = (int) SmartDashboard.getNumber("/rectangle/RIGHT");
    		visionSetpoints = vision.calculateSetpoints(rectLeft, rectRight, rectTop, rectBottom);
    	}
    	
    	
    	if(oi.xboxBtnLBumper) { //vision control part 2
    		vision.resetI();
    		visionMotors = vision.navXVisionSub();
    		drivebase.drive(visionMotors[0], visionMotors[1]);
    	}
    	else {
    		drivebase.drive(oi.lStickY, oi.rStickY); //drive the robot based on inputs from OI
    	}
        */
    	
    	/*drivebase*/
    	drivebase.drive(oi.lStickY, oi.rStickY);
 
    	/*claw*/
        clawRotation = claw.clawGetCheck(); //does tasks for the claw that happen periodically: INCLUDING GETTING CLAW ROTATION
        clawSetpoint = claw.xboxClawCtrl(clawSetpoint);   //update the clawsetpoint and check limit switch
        claw.clawSet(clawSetpoint);	  //tell claw to go to setpoint
        SmartDashboard.putNumber("clawSetpoint", clawSetpoint);
        
        /*intake*/
        intakeSetpoint = intake.intakeCtrl(intakeSetpoint, 0.08); //update intake setpoint
        SmartDashboard.putNumber("intakeSetpoint", intakeSetpoint);
        if(Utility.getUserButton()) { //zeros the intake encoder
        	intake.intakeEncZero();
        	intakeSetpoint = 0;
        }
        intake.intakeSet(intakeSetpoint); //tell intake to go to setpoint
     
        /*intake wheels*/
        intakewheels.set(intakewheels.intakeWheelsCtrl());
        
        /*pneumatics*/
        pneumatics.pneumaticsCtrl(oi.xboxBtnRBumper, oi.lStickBtn1);
        
        /*claw wheels*/
        SmartDashboard.putNumber("BEFORE wheelSetpoint", clawWheelSetpoint);
        clawWheelSetpoint = clawwheels.xboxWheelCtrl(clawWheelSetpoint);
        clawWheelSetpoint = clawwheels.realClawWheelsCtrl(clawWheelSetpoint, clawRotation, clawSetpoint); //magically figures out what the wheel setpoint should be
        clawwheels.clawWheelsSet(clawWheelSetpoint); //tell claw wheels to go to setpoint
        SmartDashboard.putNumber("wheelSetpoint", clawWheelSetpoint);
    }
    
    public void disabledPeriodic() {
    	
    	if(Utility.getUserButton()) {
    		intake.intakeEncZero();
    		intakeSetpoint = 0;
    		SmartDashboard.putString("intake reset", "INTAKE RESET");
    	}
    	else {
    		SmartDashboard.putString("intake reset", "INTAKE SUSTAINED");
    	}
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        oi.updateJoyValues();
    }
    
}
