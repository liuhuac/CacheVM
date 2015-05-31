package org.cloudbus.cloudsim.examples.cache.algorithms;

import java.util.List;

import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.examples.cache.CacheMatrix;
import org.cloudbus.cloudsim.examples.cache.CacheVm;
import org.cloudbus.cloudsim.power.PowerHost;
import org.cloudbus.cloudsim.power.PowerVm;

import org.cloudbus.cloudsim.power.PowerVmSelectionPolicy;

public class HeuristicVmSelectionPolicy extends PowerVmSelectionPolicy{

	@Override
	public Vm getVmToMigrate(PowerHost host) {
		// TODO Auto-generated method stub
		List<PowerVm> migratableVms = getMigratableVms(host);
		if (migratableVms.isEmpty()) {
			return null;
		} else if(1 == migratableVms.size()){
			return migratableVms.get(0);
		} else if(2 == migratableVms.size()){
			CacheVm vm_1 =  (CacheVm)migratableVms.get(0);
			CacheVm vm_2 =  (CacheVm)migratableVms.get(1);
			// vm with higher hits are more sensitive to cache contention
			return vm_1.getH() > vm_2.getH() ? vm_1 : vm_2;
		}
		
		double maxPain = 0.0;
		int max_i = 0;
		int max_j = 0;
		for(Vm vm_i : migratableVms){
			for(Vm vm_j : migratableVms){
				int i = vm_i.getId();
				int j = vm_j.getId();
				if(vm_i.getPainWithVm(j) > maxPain){
					maxPain = vm_i.getPainWithVm(j);
					max_i = i;
					max_j = j;
				}
			}
		}

		CacheVm vm_i =  (CacheVm)migratableVms.get(max_i);
		CacheVm vm_j =  (CacheVm)migratableVms.get(max_j);
		// vm with higher hits are more sensitive to cache contention
		CacheVm return_vm = vm_i.getH() > vm_j.getH() ? vm_i : vm_j;
		
		/*
		 * update host cache pain information
		 * in CacheMatrix.HOST_PAIN_LIST
		 */
		if(host.getVmList().size() > 1){// there is other vms besides the new vm
			double sum_pain = 0.0;
			for(Vm v : host.getVmList()){
				int e_id = v.getId(); // existing vm id
				int v_id = return_vm.getId(); // new vm id
				if( e_id == v_id){
					continue;
				} else {
					sum_pain += return_vm.getPainWithVm(e_id);
				}
			}
			int host_id = host.getId();
			double current = CacheMatrix.HOST_PAIN_LIST.get(host_id);
			CacheMatrix.HOST_PAIN_LIST.set(host_id, current - sum_pain);
		}
		/*
		 * end update host cache pain information
		 */
		return return_vm;
	}

}
