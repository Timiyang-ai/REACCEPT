@Test
	void applyMask_F32_zero() {
		GrayF32 disparity = new GrayF32(30,40);
		GrayU8 mask = new GrayU8(disparity.width,disparity.height);

		for (int row = 6; row < 32; row++) {
			for (int col = 4; col < 25; col++) {
				mask.set(col,row,1);
			}
		}

		RectifyImageOps.applyMask(disparity,mask,0);

		for (int i = 0; i < disparity.height; i++) {
			for (int j = 0; j < disparity.width; j++) {
				if( i >= 6 && i < 32 && j >= 4 && j < 25 )
					assertEquals(  0,disparity.get(j,i), UtilEjml.TEST_F64);
				else
					assertEquals(255,disparity.get(j,i), UtilEjml.TEST_F64);
			}
		}
	}