@Test
	public void computeHistogram() {
		GrayF32 derivX = new GrayF32(width, height);
		GrayF32 derivY = new GrayF32(width, height);
		int N = 36;
		OrientationHistogramSift<GrayF32> alg = new OrientationHistogramSift<GrayF32>(N, 1.5,GrayF32.class);
		alg.setImageGradient(derivX, derivY);

		for (int degrees = 5; degrees < 360; degrees+=10) {
			double theta = UtilAngle.degreeToRadian(degrees);

			int bin = degrees * N / 360;

			ImageMiscOps.fill(derivX, (float) Math.cos(theta));
			ImageMiscOps.fill(derivY, (float) Math.sin(theta));

			alg.computeHistogram(20, 15, 3);
			checkHistogram(N, alg, theta, bin);

			alg.computeHistogram(0, 0, 3);
			checkHistogram(N, alg, theta, bin);
		}
	}