@Test
	public void fullViewLeft_calibrated() {

		IntrinsicParameters param = new IntrinsicParameters(300,320,0,150,130,width,height, false, new double[]{0.1,1e-4});

		// do nothing rectification
		DenseMatrix64F rect1 = CommonOps.identity(3);
		DenseMatrix64F rect2 = CommonOps.identity(3);
		DenseMatrix64F rectK = UtilIntrinsic.calibrationMatrix(param,null);

		RectifyImageOps.fullViewLeft(param,rect1,rect2,rectK);

		// check left image
		PointTransform_F32 tran = RectifyImageOps.rectifyTransform(param, rect1);
		checkInside(tran);
		// the right view is not checked since it is not part of the contract
	}