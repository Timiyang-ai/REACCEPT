@Test
	public void optimizedParam_perfect() {

		PlanarCalibrationTarget config = GenericCalibrationGrid.createStandardConfig();
		List<Point2D_F64> grid = config.points;
		Zhang99Parameters initial = GenericCalibrationGrid.createStandardParam(true,2,3,rand);
		Zhang99Parameters found = new Zhang99Parameters(true,2,false,3);// TODO add in includeTangential?

		List<List<Point2D_F64>> observations = GenericCalibrationGrid.createObservations(initial,grid);

		CalibrationPlanarGridZhang99 alg = new CalibrationPlanarGridZhang99(config,true,2);
		assertTrue(alg.optimizedParam(observations, grid, initial, found,null));

		checkEquals(initial, found,initial);
	}