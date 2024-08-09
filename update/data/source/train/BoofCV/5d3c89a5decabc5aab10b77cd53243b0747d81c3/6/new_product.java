public static Point2Transform2_F32 transformPixelToRectNorm(CameraPinholeRadial param,
																FMatrixRMaj rectify,
																FMatrixRMaj rectifyK) {
		return ImplRectifyImageOps_F32.transformPixelToRectNorm(param, rectify, rectifyK);
	}