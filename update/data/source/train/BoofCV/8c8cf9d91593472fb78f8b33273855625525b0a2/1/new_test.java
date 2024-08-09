@Test
	void computeJointHistogram_SkipInvalid() {
		GrayU8 disparity = new GrayU8(width, height);

		ImageMiscOps.fillUniform(left,rand,10,100);
		right.setTo(left);

		// set the top half of the image to have invalid disparities
		int invalid = 255;
		ImageMiscOps.fillRectangle(disparity,invalid,0,0, width, height /2);

		StereoMutualInformation alg = new StereoMutualInformation();
		alg.configureHistogram(250,250);
		alg.computeJointHistogram(left,right,0,disparity,invalid);

		int found = ImageStatistics.sum(alg.histJoint);
		assertEquals(width * height /2,found);
	}