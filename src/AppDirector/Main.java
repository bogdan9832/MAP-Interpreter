package AppDirector;

import Repository.Repository;
import View.View;
import Controller.Controller;
import Model.Expresions.ArithmeticExpression;
import Model.Expresions.ConstantExpression;
import Model.Expresions.VariableExpression;
import Model.Statements.AssignmentStatement;
import Model.Statements.CompoundStatement;
import Model.Statements.IfStatement;
import Model.Statements.PrintStatement;
import Model.Utils.*;
public class Main {
	
	
	public static void main(String[] args) {
		Repository repo = new Repository("src/Logs/Repository/ProgramState");
		// v = 2; print(v)
		ProgramState s = new ProgramState(new ExecutionStack(),new Output() ,new SymbolTable());
		AssignmentStatement as1 = new AssignmentStatement("v",new ConstantExpression(2));
		PrintStatement ps1 = new PrintStatement(new VariableExpression("v"));
		s.addStatement(new CompoundStatement(as1,ps1));
		repo.addProgramState(s);
		/*
		 * a=2+3*5;
		 * b=a-4/2 + 7;
		 * Print(b)
		 * */
		ProgramState s2 = new ProgramState(new ExecutionStack(),new Output() ,new SymbolTable());
		AssignmentStatement as2 = new AssignmentStatement("a",
									new ArithmeticExpression(
										'+',
										new ConstantExpression(2),
										new ArithmeticExpression(
											'*',
											new ConstantExpression(3),
											new ConstantExpression(5)
									
										)
									)
								);
		AssignmentStatement as3 = new AssignmentStatement("b",
										new ArithmeticExpression('+',
											new ArithmeticExpression('-',
													new VariableExpression("a"),
													new ArithmeticExpression('/',
														new ConstantExpression(4),
														new ConstantExpression(2)
													)
														
												),
											new ConstantExpression(7)
										)
									);
		
		PrintStatement ps2 = new PrintStatement(new VariableExpression("b"));
		
		s2.addStatement(new CompoundStatement(
				new CompoundStatement(
				as2,as3
				),
				ps2
		));
		repo.addProgramState(s2);
		
		/*
		 * 	a=2-2;
		 *	If a Then v=2 Else v=3;
		 *	Print(v)
		 */
		ProgramState s3 = new ProgramState(new ExecutionStack(),new Output() ,new SymbolTable());
		PrintStatement ps3 = new PrintStatement(new VariableExpression("v"));
		IfStatement condS = new IfStatement(
				new VariableExpression("a"),
				new AssignmentStatement("v",new ConstantExpression(2)),
				new AssignmentStatement("v",new ConstantExpression(3))
		);
		AssignmentStatement as4 = new AssignmentStatement(
				"a",
				new ArithmeticExpression(
						'-',
						new ConstantExpression(2),
						new ConstantExpression(2)
						)
				);
		s3.addStatement(
				new CompoundStatement(
						as4,
						new CompoundStatement(
							condS,
							ps3
						)
				)
		);
		repo.addProgramState(s3);
		View v = new View();
		Controller c = new Controller(repo,v);
		c.mainLoop();
	}
}
