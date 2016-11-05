package org.usfirst.frc.team303.robot;
import java.util.ArrayList;

public class Auto {

	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	public Auto(String mode){
		arr.add(new DriveSeconds(1000,1));
		arr.add(new Wait(1000));
		arr.add(new DriveEncoders(1000,.7,2));
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new DriveEncoders(100,.7,2));
		para1Con.add(new Wait(1000));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		arr.add(new ParallelAction(para1Con,para1NonCon));
	}
	
	
	public void run(){
		if(arr.get(taskNum).isFinished())
			taskNum++;
		arr.get(taskNum).run();
	}
	
}
