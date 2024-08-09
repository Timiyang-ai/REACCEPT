@Test
	public void initialParam() {
		List<Point2D_F64> grid = GenericCalibrationGrid.standardLayout();
		Zhang99ParamAll initial = GenericCalibrationGrid.createEasierParam(true, 2, false, 3, rand);
		// tangential can't be linearly estimated

		List<List<Point2D_F64>> observations = GenericCalibrationGrid.createObservations(initial,grid);

		Helper alg = new Helper(grid,true,2,false);

		Zhang99ParamAll found = alg.initialParam(observations);

		checkIntrinsicOnly(initial, found, 0.01, 0.1, 0.1);
	}