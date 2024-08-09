@Test
	public void initialParam() {
		PlanarCalibrationTarget config = GenericCalibrationGrid.createStandardConfig();
		List<Point2D_F64> grid = config.points;
		Zhang99Parameters initial = GenericCalibrationGrid.createStandardParam(true,2,3,rand);

		List<List<Point2D_F64>> observations = GenericCalibrationGrid.createObservations(initial,grid);

		Helper alg = new Helper(config,true,2);

		Zhang99Parameters found = alg.initialParam(observations);

		checkIntrinsicOnly(initial, found,0.01,0.1);
	}