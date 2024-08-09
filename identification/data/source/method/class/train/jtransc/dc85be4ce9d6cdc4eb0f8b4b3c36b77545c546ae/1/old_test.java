	static private void cbrt() {
		System.out.println("cbrt:");
		//for (double v : new double[] { 1.0, 3.0, 9.0, 27.0 }) {
		for (double v : new double[]{1.0, 27.0}) {
			System.out.print(Math.cbrt(v) + ",");
		}
		System.out.println();
	}