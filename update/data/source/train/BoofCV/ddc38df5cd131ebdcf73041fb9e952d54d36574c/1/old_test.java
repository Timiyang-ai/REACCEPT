@Test
	public void linearEstimate() {

		List<Point2D_F64> grid = GenericCalibrationGrid.standardLayout();
		for( Zhang99IntrinsicParam intrinsic : createParametersForLinearTest(rand) ) {
			Zhang99AllParam expected = GenericCalibrationGrid.createStandardParam(intrinsic,3,rand);
			List<CalibrationObservation> observations = GenericCalibrationGrid.createObservations(expected, grid);

			CalibrationPlanarGridZhang99 alg = new CalibrationPlanarGridZhang99(grid, intrinsic.createLike());

			Zhang99AllParam found = expected.createLike();
			alg.linearEstimate(observations, found);

			checkIntrinsicOnly(
					(CM) expected.getIntrinsic().getCameraModel(),
					(CM) found.getIntrinsic().getCameraModel(), 0.01, 0.1, 0.1);
		}
	}