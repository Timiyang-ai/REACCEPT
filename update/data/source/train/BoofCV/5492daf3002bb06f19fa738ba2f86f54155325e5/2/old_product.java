protected ParametersZhang99 initialParam( List<List<Point2D_F64>> observations )
	{
		List<DenseMatrix64F> homographies = new ArrayList<DenseMatrix64F>();
		List<Se3_F64> motions = new ArrayList<Se3_F64>();

		for( List<Point2D_F64> obs : observations ) {
			if( !computeHomography.computeHomography(obs) )
				return null;

			DenseMatrix64F H = computeHomography.getHomography();

			homographies.add(H);
		}

		computeK.process(homographies);

		DenseMatrix64F K = computeK.getCalibrationMatrix();

		decomposeH.setCalibrationMatrix(K);
		for( DenseMatrix64F H : homographies ) {
			motions.add(decomposeH.decompose(H));
		}

		computeRadial.process(K,homographies,observations);

		double distort[] = computeRadial.getParameters();

		return convertIntoZhangParam(motions, K, distort);
	}