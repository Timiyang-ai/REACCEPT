@Test
	public void fullViewLeft_calibrated() {

		CameraPinholeRadial param =
				new CameraPinholeRadial().fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1,1e-4);

		// do nothing rectification
		RowMatrix_F32 rect1 = CommonOps_R32.identity(3);
		RowMatrix_F32 rect2 = CommonOps_R32.identity(3);
		RowMatrix_F32 rectK = PerspectiveOps.calibrationMatrix(param, (RowMatrix_F32)null);

		RectifyImageOps.fullViewLeft(param,rect1,rect2,rectK);

		// check left image
		Point2Transform2_F32 tran = RectifyImageOps.transformPixelToRect(param, rect1);
		checkInside(tran);
		// the right view is not checked since it is not part of the contract
	}