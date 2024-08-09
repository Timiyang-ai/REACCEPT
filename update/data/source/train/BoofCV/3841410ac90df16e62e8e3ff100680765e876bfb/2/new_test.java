@Test
	public void initialParam() {
		List<Point2D_F64> grid = GenericCalibrationGrid.standardLayout();
		Zhang99AllParam initial = GenericCalibrationGrid.createEasierParam(true, 2, false, 3, rand);
		// tangential can't be linearly estimated

		List<CalibrationObservation> observations = GenericCalibrationGrid.createObservations(initial,grid);

		Helper alg = new Helper(grid,true,2,false);

		Zhang99AllParam found = initial.createLike();
		alg.initialParam(observations,found);

		checkIntrinsicOnly(
				(CameraPinholeRadial)initial.getIntrinsic().getCameraModel(),
				(CameraPinholeRadial)found.getIntrinsic().getCameraModel(), 0.01, 0.1, 0.1);
	}