@Test
	public void optimizedParam_noisy() {

		List<Point2D_F64> grid = GenericCalibrationGrid.standardLayout();
		Zhang99AllParam initial = GenericCalibrationGrid.createStandardParam(true,2,true,3,rand);
		Zhang99AllParam expected = initial.copy();
		Zhang99AllParam found = new Zhang99AllParam(new CalibParamPinholeRadial(true,2,true),3);

		List<CalibrationObservation> observations = GenericCalibrationGrid.createObservations(initial,grid);

		// add a tinny bit of noise
		CameraPinholeRadial intrinsic = initial.getIntrinsic().getCameraModel();
		intrinsic.fx += rand.nextDouble()*0.01*Math.abs(intrinsic.fx);
		intrinsic.fy += rand.nextDouble()*0.01*Math.abs(intrinsic.fy);
		intrinsic.skew += rand.nextDouble()*0.01*Math.abs(intrinsic.skew);
		intrinsic.cx += rand.nextDouble()*0.01*Math.abs(intrinsic.cx);
		intrinsic.cy += rand.nextDouble()*0.01*Math.abs(intrinsic.cy);

		for( int i = 0; i < intrinsic.radial.length; i++ ) {
			intrinsic.radial[i] = rand.nextGaussian()*intrinsic.radial[i]*0.1;
		}

		CalibrationPlanarGridZhang99 alg = new CalibrationPlanarGridZhang99(grid,new CalibParamPinholeRadial(true,2,true));
		assertTrue(alg.optimizedParam(observations, grid, initial, found,null));

		checkEquals(expected, found, initial);
	}