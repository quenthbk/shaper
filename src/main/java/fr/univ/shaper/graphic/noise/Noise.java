package fr.univ.shaper.graphic.noise;

import java.util.Random;

public class Noise {
	private static final Random r=new Random();

	public static double getNoise() {
		return 5-10*r.nextDouble();
	}

	public static double getNoise(double variant) {
		return (2*r.nextDouble()-1)*variant/5;
	}

	public static double getNoise(double noise, double variant) {
		return (2*(noise/10+5)-1)*variant/5;
	}
}

