protected Zhang99ParamAll initialParam( List<CalibrationObservation> observations )
	{
		status("Estimating Homographies");
		List<DMatrixRMaj> homographies = new ArrayList<>();
		List<Se3_F64> motions = new ArrayList<>();

		for( CalibrationObservation obs : observations ) {
			if( !computeHomography.computeHomography(obs) )
				return null;

			DMatrixRMaj H = computeHomography.getHomography();

			homographies.add(H);
		}

		status("Estimating Calibration Matrix");
		computeK.process(homographies);

		DMatrixRMaj K = computeK.getCalibrationMatrix();

		decomposeH.setCalibrationMatrix(K);
		for( DMatrixRMaj H : homographies ) {
			motions.add(decomposeH.decompose(H));
		}

		status("Estimating Radial Distortion");
		computeRadial.process(K, homographies, observations);

		double distort[] = computeRadial.getParameters();

		return convertIntoZhangParam(motions, K,optimized.assumeZeroSkew, distort,
				optimized.includeTangential);
	}