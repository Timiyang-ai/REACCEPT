@Test
	public void optimizedParam_noisy() {

		PlanarCalibrationTarget config = GenericCalibrationGrid.createStandardConfig();
		List<Point2D_F64> grid = config.points;
		ParametersZhang99 initial = GenericCalibrationGrid.createStandardParam(true,2,3,rand);
		ParametersZhang99 expected = initial.copy();
		ParametersZhang99 found = new ParametersZhang99(2,3);

		List<List<Point2D_F64>> observations = GenericCalibrationGrid.createObservations(initial,grid);

		// add a tinny bit of noise
		initial.a += rand.nextDouble()*0.01*Math.abs(initial.a);
		initial.b += rand.nextDouble()*0.01*Math.abs(initial.b);
		initial.c += rand.nextDouble()*0.01*Math.abs(initial.c);
		initial.x0 += rand.nextDouble()*0.01*Math.abs(initial.x0);
		initial.y0 += rand.nextDouble()*0.01*Math.abs(initial.y0);

		for( int i = 0; i < expected.distortion.length; i++ ) {
			initial.distortion[i] = rand.nextGaussian()*expected.distortion[i]*0.1;
		}

		assertTrue(CalibrationPlanarGridZhang99.optimizedParam(observations, grid, true, initial, found));

		checkEquals(expected, found, initial);
	}