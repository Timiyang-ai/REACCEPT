public static double rint(double a) {
		double r = (double) (int) a;
		if (a < 0 && r == 0.0) return -0.0;
		return r;
	}