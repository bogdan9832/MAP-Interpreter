package Controller;

import Controller.Interfaces.IController;
import Model.Utils.Interfaces.IProgramState;
import Repository.Interfaces.IRepository;
import View.IView;

public class Controller implements IController {

	IRepository repo;
	IView view;
	public boolean stopEveryStep = false;
	public Controller(IRepository r , IView v) {
		repo = r;
		view = v;
	}
	public void runProgram(IProgramState s) {
		boolean isEmpty = false;
		view.displayStatus(s);
		while(!isEmpty) {
			try {
				s.executeNextStep();
				view.displayStatus(s);
				
			} catch (Exception e) {
				isEmpty = true;
				view.printMessage(e.getMessage());
			}
			
			if(stopEveryStep == true && view.getMessage("Do you want to continue? (y/n)").equals("n"))
				break;
			repo.logProgramStates();	
			
		}
	}
	public void mainLoop() {
		boolean running = true;
		while(running) {
			int opt =  view.run();
			switch(opt) {
			case 0:
				view.printMessage("App exiting");
				running = false;
				break;
			default:
				if(opt < 0 || opt > repo.getSize()) 
					view.printMessage("No program found with id " + opt);					
				else						
					runProgram(repo.getProgramState(opt - 1));			
				
				break;
			}
		}
	}
	

}
