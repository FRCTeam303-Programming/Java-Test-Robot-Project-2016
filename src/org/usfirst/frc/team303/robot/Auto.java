package org.usfirst.frc.team303.robot;
import java.util.ArrayList;

public class Auto {

	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	public Auto(String mode){ 
		
		switch(Robot.autoSelected1){
		case(Robot.lowBarLowGoal):
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new DriveEncoders(1480,.9,-.01));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		para1NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(para1Con,para1NonCon));
		
		ArrayList<Action>para2Con= new ArrayList<Action>();
		para2Con.add(new TurnNavX(57, .04, 0.055, 0.0003));
		ArrayList<Action>para2NonCon= new ArrayList<Action>();
		para2NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(para2Con,para2NonCon));
		
		ArrayList<Action>para3Con= new ArrayList<Action>();
		para3Con.add(new DriveEncoders(940,.9,-.01));
		ArrayList<Action>para3NonCon= new ArrayList<Action>();
		para3NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(para3Con,para3NonCon));
		
		ArrayList<Action>para4Con= new ArrayList<Action>();
		para4Con.add(new Wait(3));
		ArrayList<Action>para4NonCon= new ArrayList<Action>();
		para4NonCon.add(new setIntake(0.4));
		para4NonCon.add(new ActionSetIntakeWheels(1));
		para4NonCon.add(new ActionSetClawWheels(2000));
		arr.add(new ParallelAction(para4Con,para4NonCon));
		
		arr.add(new Wait(16000));
		break;
		
		case(Robot.lowBar):
		ArrayList<Action>Apara1Con= new ArrayList<Action>();
		Apara1Con.add(new DriveEncoders(1000,.9,-.01));
		ArrayList<Action>Apara1NonCon= new ArrayList<Action>();
		Apara1NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(Apara1Con,Apara1NonCon));
		break;
		
		case(Robot.rockWall):
		ArrayList<Action>Bpara1Con= new ArrayList<Action>();
		Bpara1Con.add(new DriveEncoders(700,1,-.01));
		ArrayList<Action>Bpara1NonCon= new ArrayList<Action>();
		Bpara1NonCon.add(new setIntake(0.8));
		arr.add(new ParallelAction(Bpara1Con,Bpara1NonCon));
		break;
		case(Robot.shovelTheFries):
		ArrayList<Action>Cpara1Con= new ArrayList<Action>();
		Cpara1Con.add(new DriveEncoders(330,.7,-.01));
		ArrayList<Action>Cpara1NonCon= new ArrayList<Action>();
		Cpara1NonCon.add(new setIntake(1.4));
		arr.add(new ParallelAction(Cpara1Con,Cpara1NonCon));
		
		ArrayList<Action>Cpara2Con= new ArrayList<Action>();
		Cpara2Con.add(new Wait(1.5));
		ArrayList<Action>Cpara2NonCon= new ArrayList<Action>();
		Cpara2NonCon.add(new setIntake(0));
		arr.add(new ParallelAction(Cpara2Con,Cpara2NonCon));
		
		ArrayList<Action>Cpara3Con= new ArrayList<Action>();
		Cpara3Con.add(new DriveEncoders(600,.7,-.01));
		ArrayList<Action>Cpara3NonCon= new ArrayList<Action>();
		Cpara3NonCon.add(new setIntake(1));
		arr.add(new ParallelAction(Cpara3Con,Cpara3NonCon));
		
		break;
		
		
		default:
			//default auto is do nothing so there is no code ;)
			break;
			
	}
	}
	
	
	public void run(){
		if(arr.get(taskNum).isFinished())
			taskNum++;
		arr.get(taskNum).run();
	}
	
}
