package org.cloudbus.cloudsim.examples.cache.ilp;

import ilog.concert.*;
import ilog.cplex.*;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		model1();

	}
	
	public static void model1() {
		try {
			IloCplex cplex = new IloCplex();
			
			//variables
			IloNumVar x = cplex.numVar(0, Double.MAX_VALUE, "x");
			IloNumVar y = cplex.numVar(0, Double.MAX_VALUE, "y");
			
			// expressions
			IloLinearNumExpr objective = cplex.linearNumExpr();
			objective.addTerm(0.12, x);
			objective.addTerm(0.15, y);
			
			// define objective
			cplex.addMinimize(objective);
			
			// define constraints
			cplex.addGe(cplex.sum(cplex.prod(60, x),cplex.prod(60, y)), 300);
			cplex.addGe(cplex.sum(cplex.prod(12, x),cplex.prod(6, y)), 36);
			cplex.addGe(cplex.sum(cplex.prod(10, x),cplex.prod(30, y)), 90);
			
			// sove
			if(cplex.solve()){
				System.out.println("obj = "+cplex.getObjValue());
				System.out.println("x = "+cplex.getValue(x));
				System.out.println("y = "+cplex.getValue(y));
			} else {
				
			}
			
		}
		catch (IloException exc) {
			exc.printStackTrace();
		}
	}

}
