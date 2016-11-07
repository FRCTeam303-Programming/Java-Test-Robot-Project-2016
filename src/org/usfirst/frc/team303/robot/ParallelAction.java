package org.usfirst.frc.team303.robot;
import java.util.ArrayList;

public class ParallelAction implements Action{
	ArrayList<Action> conditionActions=new ArrayList<Action>();//actions that control when it is finished
	ArrayList<Action> nonConditionActions=new ArrayList<Action>(); //actions that run and do not control when finished
	public ParallelAction(ArrayList<Action> conditionActionsC, ArrayList<Action> nonConditionActionsC){
		conditionActions=conditionActionsC;
		nonConditionActions=nonConditionActionsC;
	}
	public ParallelAction(ArrayList<Action> conditionActionsC){
		this(conditionActionsC,new ArrayList<Action>());
	}
	public void run(){
		for(Action e:conditionActions){
			e.run();
		}
		for(Action e:nonConditionActions){
			e.run();
		}
	}
	public boolean isFinished(){
		for(Action e:conditionActions){
			if(!e.isFinished())
				return false;
		}
		return true;
		
	}
}
