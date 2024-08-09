@Test
	void process_small() {
		w = 5;
		RenderCalibrationTargetsGraphics2D renderer = new RenderCalibrationTargetsGraphics2D(p,1);
		renderer.chessboard(rows,cols,w);

		DetectChessboardCorners<GrayF32,GrayF32> alg = new DetectChessboardCorners<>(GrayF32.class);
		alg.setKernelRadius(1); // should fail if 2
		checkSolution(renderer.getGrayF32(), alg);
	}