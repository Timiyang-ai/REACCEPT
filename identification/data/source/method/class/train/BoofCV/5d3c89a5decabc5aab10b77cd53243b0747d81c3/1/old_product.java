public static Point2Transform2_F32 transformPixelToRectNorm(CameraPinholeRadial param,
																RowMatrix_F32 rectify,
																RowMatrix_F32 rectifyK) {
		return ImplRectifyImageOps_F32.transformPixelToRectNorm(param, rectify, rectifyK);
	}