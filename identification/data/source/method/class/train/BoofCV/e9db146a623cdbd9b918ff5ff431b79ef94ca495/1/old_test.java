@Test
	public void fullViewLeft_calibrated() {

		CameraPinholeBrown param =
				new CameraPinholeBrown().fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1,1e-4);

		// do nothing rectification
		FMatrixRMaj rect1 = CommonOps_FDRM.identity(3);
		FMatrixRMaj rect2 = CommonOps_FDRM.identity(3);
		FMatrixRMaj rectK = PerspectiveOps.pinholeToMatrix(param, (FMatrixRMaj)null);

		RectifyImageOps.fullViewLeft(param,rect1,rect2,rectK);

		// check left image
		Point2Transform2_F32 tran = RectifyImageOps.transformPixelToRect(param, rect1);
		checkInside(tran);
		// the right view is not checked since it is not part of the contract
	}