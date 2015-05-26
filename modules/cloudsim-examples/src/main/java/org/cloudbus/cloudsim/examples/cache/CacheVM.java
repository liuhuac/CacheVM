package org.cloudbus.cloudsim.examples.cache;

import org.cloudbus.cloudsim.examples.cache.Constants;
import org.cloudbus.cloudsim.examples.power.random.RandomRunner;

public class CacheVM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean enableOutput = true;
		boolean outputToFile = true;
		String inputFolder = "workloads";
		String outputFolder = "results";
		String workload = Constants.WORKLOAD; // workload
		String vmAllocationPolicy = "cacheVM"; // 
		String vmSelectionPolicy = "cacheVM";
		String parameter = Constants.STATIC_THRESHOLD;

		new RandomRunner(
				enableOutput,
				outputToFile,
				inputFolder,
				outputFolder,
				workload,
				vmAllocationPolicy,
				vmSelectionPolicy,
				parameter);
	}

}
