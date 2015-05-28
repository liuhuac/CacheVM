package org.cloudbus.cloudsim.examples.cache;

import org.cloudbus.cloudsim.examples.cache.algorithms.ClassificationAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.HeuristicAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.MissrateAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.OptimalAlgorithm;
import org.cloudbus.cloudsim.examples.cache.algorithms.RandomAlgorithm;

public class Simulation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		HeuristicAlgorithm.main(args);
		OptimalAlgorithm.main(args);
		ClassificationAlgorithm.main(args);
		MissrateAlgorithm.main(args);

	}

}
