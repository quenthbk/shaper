package fr.univ.shaper.core.noise;

import java.util.Random;

public class Noise {
	private static final Random r=new Random();

	public static double getNoise() {
		return 5-10*r.nextDouble();
	}

	public static double getNoise(double variant) {
		return (2*r.nextDouble()-1)*variant/5;
	}
}

