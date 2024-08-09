public static void fullViewLeft(CameraPinholeBrown paramLeft,
									@Nullable DMatrixRMaj rectifiedR, DMatrixRMaj rectifyLeft, DMatrixRMaj rectifyRight,
									DMatrixRMaj rectifyK, @Nullable ImageDimension rectifiedSize)
	{
		if( rectifiedSize == null )
			rectifiedSize = new ImageDimension();

		ImplRectifyImageOps_F64.fullViewLeft(paramLeft,rectifiedR, rectifyLeft, rectifyRight, rectifyK,rectifiedSize);
	}