package org.usfirst.frc.team303.robot;
import java.util.ArrayList;

public class Auto {

	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	public Auto(String mode){ 
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new DriveEncoders(1560,.9,-.01));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		para1NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(para1Con,para1NonCon));
		
		ArrayList<Action>para2Con= new ArrayList<Action>();
		para2Con.add(new TurnNavX(55, .04, 0.055, 0.0003));
		ArrayList<Action>para2NonCon= new ArrayList<Action>();
		para2NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(para2Con,para2NonCon));
		
		ArrayList<Action>para3Con= new ArrayList<Action>();
		para3Con.add(new DriveEncoders(860,.9,-.01));
		ArrayList<Action>para3NonCon= new ArrayList<Action>();
		para3NonCon.add(new setIntake(0.4));
		arr.add(new ParallelAction(para3Con,para3NonCon));
		
		arr.add(new Wait(16000));
	}
	
	
	public void run(){
		if(arr.get(taskNum).isFinished())
			taskNum++;
		arr.get(taskNum).run();
	}
	
}
