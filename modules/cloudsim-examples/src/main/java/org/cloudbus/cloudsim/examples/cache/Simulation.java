package org.cloudbus.cloudsim.examples.cache;

import org.cloudbus.cloudsim.examples.cache.algorithms.ClassificationAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.HeuristicAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.MissrateAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.OptimalAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.RandomAlgorithm;

public class Simulation {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		/*
		 * Either initialize the CacheMatrix here
		 *  to ensure using the same CacheMatrix for
		 * different allocation algorithms
		 * or initialize it in ExpRunner.init()
		 * for a specific algorithm.
		 */
		CacheMatrix cm = new CacheMatrix();
		cm.init();
		
		RandomAlgorithm.main(args);
		PrintVm2PmMapping.print();
		
		HeuristicAlgorithm.main(args);
		PrintVm2PmMapping.print();
		
		//OptimalAlgorithm.main(args);
		
		ClassificationAlgorithm.main(args);
		PrintVm2PmMapping.print();
		
		MissrateAlgorithm.main(args);
		PrintVm2PmMapping.print();

	}

}
