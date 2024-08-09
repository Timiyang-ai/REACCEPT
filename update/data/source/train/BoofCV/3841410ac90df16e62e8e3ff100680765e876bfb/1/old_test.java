@Test
	public void optimizedParam_noisy() {

		List<Point2D_F64> grid = GenericCalibrationGrid.standardLayout();
		Zhang99ParamAll initial = GenericCalibrationGrid.createStandardParam(true,2,true,3,rand);
		Zhang99ParamAll expected = initial.copy();
		Zhang99ParamAll found = new Zhang99ParamAll(true,2,true,3);

		List<CalibrationObservation> observations = GenericCalibrationGrid.createObservations(initial,grid);

		// add a tinny bit of noise
		initial.a += rand.nextDouble()*0.01*Math.abs(initial.a);
		initial.b += rand.nextDouble()*0.01*Math.abs(initial.b);
		initial.c += rand.nextDouble()*0.01*Math.abs(initial.c);
		initial.x0 += rand.nextDouble()*0.01*Math.abs(initial.x0);
		initial.y0 += rand.nextDouble()*0.01*Math.abs(initial.y0);

		for( int i = 0; i < expected.radial.length; i++ ) {
			initial.radial[i] = rand.nextGaussian()*expected.radial[i]*0.1;
		}

		CalibrationPlanarGridZhang99 alg = new CalibrationPlanarGridZhang99(grid,true,2,true);
		assertTrue(alg.optimizedParam(observations, grid, initial, found,null));

		checkEquals(expected, found, initial);
	}