protected Zhang99ParamAll initialParam( List<CalibrationObservation> observations )
	{
		status("Estimating Homographies");
		List<DenseMatrix64F> homographies = new ArrayList<DenseMatrix64F>();
		List<Se3_F64> motions = new ArrayList<Se3_F64>();

		for( CalibrationObservation obs : observations ) {
			if( !computeHomography.computeHomography(obs) )
				return null;

			DenseMatrix64F H = computeHomography.getHomography();

			homographies.add(H);
		}

		status("Estimating Calibration Matrix");
		computeK.process(homographies);

		DenseMatrix64F K = computeK.getCalibrationMatrix();

		decomposeH.setCalibrationMatrix(K);
		for( DenseMatrix64F H : homographies ) {
			motions.add(decomposeH.decompose(H));
		}

		status("Estimating Radial Distortion");
		computeRadial.process(K, homographies, observations);

		double distort[] = computeRadial.getParameters();

		return convertIntoZhangParam(motions, K,optimized.assumeZeroSkew, distort,
				optimized.includeTangential);
	}