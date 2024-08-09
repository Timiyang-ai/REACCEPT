static public double cbrt(double x) {
		double y = Math.pow(Math.abs(x), 1.0 / 3.0);
		return (x < 0) ? -y : y;
	}