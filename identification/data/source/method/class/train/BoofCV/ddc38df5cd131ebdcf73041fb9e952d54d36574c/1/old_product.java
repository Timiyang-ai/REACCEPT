protected boolean linearEstimate(List<CalibrationObservation> observations , Zhang99AllParam param )
	{
		status("Estimating Homographies");
		List<DMatrixRMaj> homographies = new ArrayList<>();
		List<Se3_F64> motions = new ArrayList<>();

		for( CalibrationObservation obs : observations ) {
			if( !computeHomography.computeHomography(obs) )
				return false;

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

		convertIntoZhangParam(motions, K,distort, param);
		return true;
	}