package org.cloudbus.cloudsim.examples.cache;

import org.cloudbus.cloudsim.VmAllocationPolicy;
import org.cloudbus.cloudsim.examples.power.random.RandomRunner;
import org.cloudbus.cloudsim.power.PowerVmAllocationPolicyMigrationStaticThreshold;
import org.cloudbus.cloudsim.power.PowerVmAllocationPolicySimple;
import org.cloudbus.cloudsim.power.PowerVmSelectionPolicy;

public class CacheRunner extends RandomRunner {
	
	public CacheRunner(
			boolean enableOutput,
			boolean outputToFile,
			String inputFolder,
			String outputFolder,
			String workload,
			String vmAllocationPolicy,
			String vmSelectionPolicy,
			String parameter) {
		super(
				enableOutput,
				outputToFile,
				inputFolder,
				outputFolder,
				workload,
				vmAllocationPolicy,
				vmSelectionPolicy,
				parameter);
	}
	
	/**
	 * Gets the vm allocation policy.
	 * 
	 * @param vmAllocationPolicyName the vm allocation policy name
	 * @param vmSelectionPolicyName the vm selection policy name
	 * @param parameterName the parameter name
	 * @return the vm allocation policy
	 */
	protected VmAllocationPolicy getVmAllocationPolicy(
			String vmAllocationPolicyName,
			String vmSelectionPolicyName,
			String parameterName) {
		VmAllocationPolicy vmAllocationPolicy = null;
		PowerVmSelectionPolicy vmSelectionPolicy = null;
		if (!vmSelectionPolicyName.isEmpty()) {
			vmSelectionPolicy = getVmSelectionPolicy(vmSelectionPolicyName);
		}
		double parameter = 0;
		if (!parameterName.isEmpty()) {
			parameter = Double.valueOf(parameterName);
		}
		if (vmAllocationPolicyName.equals("cacheVM")) {
			vmAllocationPolicy = new PowerVmAllocationPolicyMigrationStaticThreshold(
					hostList,
					vmSelectionPolicy,
					parameter);
		} else if (vmAllocationPolicyName.equals("dvfs")) {
			vmAllocationPolicy = new PowerVmAllocationPolicySimple(hostList);
		} else {
			System.out.println("Unknown VM allocation policy: " + vmAllocationPolicyName);
			System.exit(0);
		}
		return vmAllocationPolicy;
	}

}
