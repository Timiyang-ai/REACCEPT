@Test
	public void computePointsAndWeights() {
		for (Class imageType : imageTypes) {
			computePointsAndWeights(true, imageType);
			computePointsAndWeights(false, imageType);
		}
	}