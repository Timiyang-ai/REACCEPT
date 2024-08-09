protected Zhang99Parameters initialParam( List<List<Point2D_F64>> observations )
	{
		status("Estimating Homographies");
		List<DenseMatrix64F> homographies = new ArrayList<DenseMatrix64F>();
		List<Se3_F64> motions = new ArrayList<Se3_F64>();

		for( List<Point2D_F64> obs : observations ) {
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
		computeRadial.process(K,homographies,observations);

		double distort[] = computeRadial.getParameters();

		return convertIntoZhangParam(motions, K,assumeZeroSkew, distort);
	}