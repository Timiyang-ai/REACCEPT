@Test
	public void initialParam() {
		PlanarCalibrationTarget config = GenericCalibrationGrid.createStandardConfig();
		List<Point2D_F64> grid = config.points;
		ParametersZhang99 initial = GenericCalibrationGrid.createStandardParam(true,2,3,rand);

		List<List<Point2D_F64>> observations = GenericCalibrationGrid.createObservations(initial,grid);

		Helper alg = new Helper(config,true,2);

		ParametersZhang99 found = alg.initialParam(observations);

		checkIntrinsicOnly(initial, found,0.01,0.1);
	}