package org.cloudbus.cloudsim.examples.cache;

import java.util.Random;

public class StackDistanceProfileModel {

	/*
	 * y = a*e^(-(1/b)*x+(c/200))
	 */
	int a,b,c;
	
	public StackDistanceProfileModel(){
		shuffle();
	}
	
	public void shuffle(){
		Random rand = new Random();
		a = rand.nextInt(200);
		b = rand.nextInt(50)+50;
		c = rand.nextInt(200);
	}
	
	public double get(int x){
		return a*Math.exp(-(1.0/b)*x+(c/200));
	}
}
