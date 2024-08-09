@Test
	void linearEstimate()
	{
		for( CameraConfig config : createCameraForLinearTests(rand) )
		{
			CalibInputs inputs = createInputs(config.model,3,rand);
			Zhang99Camera zhangCamera = createGenerator(config);

			CalibrationPlanarGridZhang99 alg = new CalibrationPlanarGridZhang99(
					inputs.layout, zhangCamera );

			alg.linearEstimate(inputs.observations);

			SceneStructureMetric structure = alg.getStructure();

			CM found = (CM)zhangCamera.getCameraModel(structure.getCameras()[0].model);

			checkIntrinsicOnly(config.model,found, 0.01, 0.1, 0.1);
		}
	}