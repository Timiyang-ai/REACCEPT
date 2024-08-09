@Test
	void computeJointHistogram_SkipInvalid() {
		GrayU8 left = new GrayU8(w,h);
		GrayU8 right = new GrayU8(w,h);
		GrayU8 disparity = new GrayU8(w,h);

		ImageMiscOps.fillUniform(left,rand,10,100);
		right.setTo(left);

		// set the top half of the image to have invalid disparities
		int invalid = 255;
		ImageMiscOps.fillRectangle(disparity,invalid,0,0,w,h/2);

		StereoMutualInformation alg = new StereoMutualInformation();
		alg.configureHistogram(250,250);
		alg.computeJointHistogram(left,right,0,disparity,invalid);

		int found = ImageStatistics.sum(alg.histJoint);
		assertEquals(w*h/2,found);
	}