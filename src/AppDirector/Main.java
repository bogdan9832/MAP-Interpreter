package AppDirector;

import Repository.Repository;
import View.TextMenu;
import Controller.Controller;
import Model.Comands.ExitCommand;
import Model.Comands.RunExample;
import Model.Expresions.ArithmeticExpression;
import Model.Expresions.ConstantExpression;
import Model.Expresions.HeapReadExpression;
import Model.Expresions.VariableExpression;
import Model.Interfaces.IExpression;
import Model.Interfaces.IStatement;
import Model.Statements.AssignmentStatement;
import Model.Statements.CloseReadFileStatement;
import Model.Statements.CompoundStatement;
import Model.Statements.HeapAllocationStatement;
import Model.Statements.HeapWritingStatement;
import Model.Statements.IfStatement;
import Model.Statements.OpenReadFileStatement;
import Model.Statements.PrintStatement;
import Model.Statements.ReadFileStatement;
import Model.Utils.*;

public class Main {

	public static void main(String[] args) {
		// v = 2; print(v)
		Repository repo1 = new Repository("src/Logs/Repository/Problem1.txt");
		ProgramState s = new ProgramState();
		AssignmentStatement as1 = new AssignmentStatement("v", new ConstantExpression(2));
		PrintStatement ps1 = new PrintStatement(new VariableExpression("v"));
		s.addStatement(new CompoundStatement(as1, ps1));
		repo1.addProgramState(s);
		Controller c1 = new Controller(repo1);

		// a=2+3*5;b=a-4/2 + 7;Print(b)

		Repository repo2 = new Repository("src/Logs/Repository/Problem2.txt");
		ProgramState s2 = new ProgramState();
		AssignmentStatement as2 = new AssignmentStatement("a", new ArithmeticExpression('+', new ConstantExpression(2),
				new ArithmeticExpression('*', new ConstantExpression(3), new ConstantExpression(5)

				)));
		AssignmentStatement as3 = new AssignmentStatement("b",
				new ArithmeticExpression('+', new ArithmeticExpression('-', new VariableExpression("a"),
						new ArithmeticExpression('/', new ConstantExpression(4), new ConstantExpression(2))

				), new ConstantExpression(7)));

		PrintStatement ps2 = new PrintStatement(new VariableExpression("b"));

		s2.addStatement(new CompoundStatement(new CompoundStatement(as2, as3), ps2));
		repo2.addProgramState(s2);
		Controller c2 = new Controller(repo2);

		// a=2-2; If a Then v=2 Else v=3; Print(v)
		Repository repo3 = new Repository("src/Logs/Repository/Problem3.txt");

		ProgramState s3 = new ProgramState();
		PrintStatement ps3 = new PrintStatement(new VariableExpression("v"));
		IfStatement condS = new IfStatement(new VariableExpression("a"),
				new AssignmentStatement("v", new ConstantExpression(2)),
				new AssignmentStatement("v", new ConstantExpression(3)));
		AssignmentStatement as4 = new AssignmentStatement("a",
				new ArithmeticExpression('-', new ConstantExpression(2), new ConstantExpression(2)));
		s3.addStatement(new CompoundStatement(as4, new CompoundStatement(condS, ps3)));
		repo3.addProgramState(s3);
		Controller c3 = new Controller(repo3);

		/*
		 * openRFile(var_f,"test.in"); readFile(var_f,var_c);print(var_c); (if var_c
		 * then readFile(var_f,var_c);print(var_c) else print(0)); closeRFile(var_f)
		 */
		Repository repo4 = new Repository("src/Logs/Repository/Problem4.txt");
		ProgramState s4 = new ProgramState();
		OpenReadFileStatement orfs1 = new OpenReadFileStatement("src/Input/test.in", "var_f");
		ReadFileStatement rfs1 = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
		PrintStatement ps4 = new PrintStatement(new VariableExpression("var_c"));
		CompoundStatement cs1 = new CompoundStatement(rfs1, ps4);
		ReadFileStatement rfs2 = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
		PrintStatement ps5 = new PrintStatement(new VariableExpression("var_c"));
		CompoundStatement cs2 = new CompoundStatement(rfs2, ps5);
		PrintStatement ps6 = new PrintStatement(new ConstantExpression(0));
		IfStatement if1 = new IfStatement(new VariableExpression("var_c"), cs2, ps6);
		CloseReadFileStatement crfs1 = new CloseReadFileStatement(new VariableExpression("var_f"));

		s4.addStatement(crfs1);
		s4.addStatement(if1);
		s4.addStatement(cs1);
		s4.addStatement(orfs1);
		repo4.addProgramState(s4);
		Controller c4 = new Controller(repo4);

		/*
		 * openRFile(var_f,"test.in"); readFile(var_f,var_c);print(var_c); (if var_c
		 * then readFile(var_f + 2,var_c);print(var_c) else print(0)); closeRFile(var_f)
		 */
		Repository repo5 = new Repository("src/Logs/Repository/Problem5.txt");
		ProgramState s5 = new ProgramState();
		OpenReadFileStatement orfs2 = new OpenReadFileStatement("src/Input/test.in", "var_f");
		ReadFileStatement rfs3 = new ReadFileStatement(
				new ArithmeticExpression('+', new VariableExpression("var_f"), new ConstantExpression(2)), "var_c");
		PrintStatement ps7 = new PrintStatement(new VariableExpression("var_c"));
		CompoundStatement cs3 = new CompoundStatement(rfs3, ps7);
		ReadFileStatement rfs4 = new ReadFileStatement(new VariableExpression("var_f"), "var_c");
		PrintStatement ps8 = new PrintStatement(new VariableExpression("var_c"));
		CompoundStatement cs4 = new CompoundStatement(rfs4, ps8);
		PrintStatement ps9 = new PrintStatement(new ConstantExpression(0));
		IfStatement if2 = new IfStatement(new VariableExpression("var_c"), cs4, ps9);
		CloseReadFileStatement crfs2 = new CloseReadFileStatement(new VariableExpression("var_f"));

		s5.addStatement(crfs2);
		s5.addStatement(if2);
		s5.addStatement(cs3);
		s5.addStatement(orfs2);
		repo5.addProgramState(s5);
		Controller c5 = new Controller(repo5);

		// v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))

		Repository repo6 = new Repository("src/Logs/Repository/Problem6.txt");
		ProgramState s6 = new ProgramState();

		IStatement st1 = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)),
				new CompoundStatement(new HeapAllocationStatement("v", new ConstantExpression(20)),
						new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)),
								new CompoundStatement(
										new PrintStatement(new ArithmeticExpression('+', new ConstantExpression(100),
												new HeapReadExpression("v"))),
										new PrintStatement(new ArithmeticExpression('+', new ConstantExpression(100),
												new HeapReadExpression("a")))))));

		s6.addStatement(st1);
		repo6.addProgramState(s6);
		Controller c6 = new Controller(repo6);

		Repository repo7 = new Repository("src/Logs/Repository/Problem6.txt");
		ProgramState s7 = new ProgramState();

		IStatement st2 = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)),
				new CompoundStatement(new HeapAllocationStatement("v", new ConstantExpression(20)),
						new CompoundStatement(new HeapAllocationStatement("a", new ConstantExpression(22)),
								new CompoundStatement(new HeapWritingStatement("a", new ConstantExpression(30)),
										new CompoundStatement(new PrintStatement(new VariableExpression("a")),
												new CompoundStatement(new PrintStatement(new HeapReadExpression("a")),
														new AssignmentStatement("a", new ConstantExpression(0))))))));

		s7.addStatement(st2);
		repo7.addProgramState(s7);
		Controller c7 = new Controller(repo7);

		TextMenu txtMenu = new TextMenu();
		txtMenu.addCommand(new ExitCommand("0", "Exit"));
		txtMenu.addCommand(new RunExample("1", "v = 2; print(v)", c1));
		txtMenu.addCommand(new RunExample("2", "a=2+3*5;b=a-4/2 + 7;Print(b)", c2));
		txtMenu.addCommand(new RunExample("3", "a=2-2; If a Then v=2 Else v=3; Print(v)", c3));
		txtMenu.addCommand(new RunExample("4", "openRFile(var_f,\"test.in\"); " + "readFile(var_f,var_c);print(var_c); "
				+ "(if var_c then readFile(var_f,var_c);print(var_c) " + "else print(0)); " + "closeRFile(var_f) ",
				c4));
		txtMenu.addCommand(new RunExample("5",
				"openRFile(var_f,\"test.in\"); " + "readFile(var_f+2,var_c);print(var_c); "
						+ "(if var_c then readFile(var_f,var_c);print(var_c) " + "else print(0)); "
						+ "closeRFile(var_f) ",
				c5));

		txtMenu.addCommand(new RunExample("6", "v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))", c6));
		txtMenu.addCommand(new RunExample("7", "v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0", c7));

		txtMenu.show();
	}
}
