package org.cloudbus.cloudsim.examples.cache;

import java.util.ArrayList;
import java.util.List;

public class CacheMatrix {
	
	public static int A = 100;
	
	/*
	 *  Each row of PROFILE_MATRIX is a stack distance profile
	 *  n_t (rows) x A (cols)  
	 *  n_t is the total number of VMs 
	 */
	public static List<List<Integer>> PROFILE_MATRIX = null;
	
	/*
	 *  Each element of PAIN_MATRIX is the pain between VM i and j
	 *  n_t (rows) x n_t (cols)
	 *  n_t is the total number of VMs  
	 */
	public static List<List<Double>> PAIN_MATRIX = null;
	
	public void init(){
		
		PROFILE_MATRIX = new ArrayList<List<Integer>>();
		
		for(int i=0; i<ExpConstants.NUMBER_OF_VMS; i++){
			PROFILE_MATRIX.add(random_profile());
		}
		
		init_pain_matrix();
	}

	private void init_pain_matrix() {
		// TODO Auto-generated method stub
		PAIN_MATRIX = new ArrayList<List<Double>>();
		
		for(int i=0; i<ExpConstants.NUMBER_OF_VMS; i++){
			List<Double> row = new ArrayList<Double>();
			PAIN_MATRIX.add(row);
		}
		
		for(int i=0; i<ExpConstants.NUMBER_OF_VMS; i++){
			for(int j=i; j<ExpConstants.NUMBER_OF_VMS; j++){
				
				if(i == j){
					PAIN_MATRIX.get(i).add((double) 0);
				}
				
				double pain_i = 0, pain_j = 0;
				double s_i = 0, s_j = 0;
				double z_i = 0, z_j = 0;
				double p_i, p_j;
				
				for(int k=0; k<A; k++){
					int data_i = PROFILE_MATRIX.get(i).get(k).intValue();
					int data_j = PROFILE_MATRIX.get(j).get(k).intValue();										
					
					if(i != A){
						if(0==data_i && 0==data_j){
							p_i = p_j = 0;
						} else {
							p_i = ((double) data_i) / (data_i + data_j);
							p_j = 1 - p_i;
						}
												
						s_i += p_j * data_i;
						s_j += p_i * data_j;
					}
														
					z_i += data_i;
					z_j += data_j;
				}
				
				pain_i = s_i * z_j;
				pain_j = s_j * z_i;
				
				PAIN_MATRIX.get(i).add((double)(pain_i+pain_j));
				PAIN_MATRIX.get(j).add((double)(pain_i+pain_j));
			}
		}
		
	}

	private List<Integer> random_profile() {
		// TODO Auto-generated method stub
		List<Integer> profile = new ArrayList<Integer>();
		StackDistanceProfileModel sdpm = new StackDistanceProfileModel();
		int i,sum=0;
		for(i=0; i<A-1; i++){
			profile.add((int)sdpm.get(i));
		}
		for(; i<A+50; i++){
			sum += (int)sdpm.get(i);
		}
		profile.add(sum);
		return profile;
	}

	private List<Integer> read_profile() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save_random_profile(String filename){
		// TODO Save the random generated profile matrix to file
	}
	
	public static double get_pain(int i, int j){
		return PAIN_MATRIX.get(i).get(j).doubleValue();
	}

}
