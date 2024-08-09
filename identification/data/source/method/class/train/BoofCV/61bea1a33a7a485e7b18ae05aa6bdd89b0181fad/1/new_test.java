@Test
	public void optimizedParam_perfect() {

		PlanarCalibrationTarget config = GenericCalibrationGrid.createStandardConfig();
		List<Point2D_F64> grid = config.points;
		Zhang99ParamAll initial = GenericCalibrationGrid.createStandardParam(true,2,true,3,rand);
		Zhang99ParamAll found = new Zhang99ParamAll(true,2,true,3);

		List<List<Point2D_F64>> observations = GenericCalibrationGrid.createObservations(initial,grid);

		CalibrationPlanarGridZhang99 alg = new CalibrationPlanarGridZhang99(config,true,2,true);
		assertTrue(alg.optimizedParam(observations, grid, initial, found,null));

		checkEquals(initial, found, initial);
	}