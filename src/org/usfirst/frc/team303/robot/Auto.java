package org.usfirst.frc.team303.robot;
import java.util.ArrayList;

public class Auto {

	ArrayList<Action> arr= new ArrayList<Action>();
	int taskNum=0;
	public Auto(String mode){ 
		//selects auto mode
		switch(Robot.autoSelected1){
		case(Robot.lowBarLowGoal):
			assembleLowBarLowGoal();
		break;
		
		case(Robot.lowBar):
			assembleLowBar();
		break;
		
		case(Robot.rockWall):
			assembleRockWall();
		break;
		
		case(Robot.shovelTheFries)://for the uninformed shovelTheFries is cheval
			assembleShovelTheFries();
		break;
		
		default:
			//default auto is do nothing so there is no code ;)
			break;
			
	}
		arr.add(new ActionWait(100000));
	}
	
	
	public void run(){
		if(arr.size()<taskNum){
			if(arr.get(taskNum).isFinished())
				taskNum++;
			arr.get(taskNum).run();
		}
	}
	private void assembleLowBarLowGoal(){
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new ActionDriveEncoders(1480,.9,-.01));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		para1NonCon.add(new ActionSetIntake(0.4));
		arr.add(new ActionParallelAction(para1Con,para1NonCon));
		
		ArrayList<Action>para2Con= new ArrayList<Action>();
		para2Con.add(new ActionTurnNavX(57, .02, 0.08, 0.0005));
		ArrayList<Action>para2NonCon= new ArrayList<Action>();
		para2NonCon.add(new ActionSetIntake(0.4));
		arr.add(new ActionParallelAction(para2Con,para2NonCon));
		
		ArrayList<Action>para3Con= new ArrayList<Action>();
		para3Con.add(new ActionDriveEncoders(940,.9,-.01));
		ArrayList<Action>para3NonCon= new ArrayList<Action>();
		para3NonCon.add(new ActionSetIntake(0.4));
		arr.add(new ActionParallelAction(para3Con,para3NonCon));
		
		ArrayList<Action>para4Con= new ArrayList<Action>();
		para4Con.add(new ActionWait(3));
		ArrayList<Action>para4NonCon= new ArrayList<Action>();
		para4NonCon.add(new ActionSetIntake(0.4));
		para4NonCon.add(new ActionSetIntakeWheels(1));
		para4NonCon.add(new ActionSetClawWheels(2000));
		arr.add(new ActionParallelAction(para4Con,para4NonCon));
		
	}
	
	private void assembleLowBar(){
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new ActionDriveEncoders(1000,.9,-.01));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		para1NonCon.add(new ActionSetIntake(0.4));
		arr.add(new ActionParallelAction(para1Con,para1NonCon));
	}
	
	private void assembleRockWall(){
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new ActionDriveEncoders(1000,1,-.01));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		para1NonCon.add(new ActionSetIntake(0.8));
		arr.add(new ActionParallelAction(para1Con,para1NonCon));
	}
	private void assembleShovelTheFries(){
		ArrayList<Action>para1Con= new ArrayList<Action>();
		para1Con.add(new ActionDriveEncoders(330,.7,-.01));
		ArrayList<Action>para1NonCon= new ArrayList<Action>();
		para1NonCon.add(new ActionSetIntake(1.4));
		arr.add(new ActionParallelAction(para1Con,para1NonCon));
		
		ArrayList<Action>para2Con= new ArrayList<Action>();
		para2Con.add(new ActionWait(1.5));
		ArrayList<Action>para2NonCon= new ArrayList<Action>();
		para2NonCon.add(new ActionSetIntake(0));
		arr.add(new ActionParallelAction(para2Con,para2NonCon));
		
		ArrayList<Action>para3Con= new ArrayList<Action>();
		para3Con.add(new ActionDriveEncoders(600,.7,-.01));
		ArrayList<Action>para3NonCon= new ArrayList<Action>();
		para3NonCon.add(new ActionSetIntake(1));
		arr.add(new ActionParallelAction(para3Con,para3NonCon));
	}
}
