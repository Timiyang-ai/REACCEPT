@Override
	public void detect(PyramidFloat<T> ss) {
		spaceIndex = 0;
		if (intensities == null) {
			intensities = new GrayF32[3];
			intensities[0] = new GrayF32(1, 1);
			intensities[1] = new GrayF32(1, 1);
			intensities[2] = new GrayF32(1, 1);

			maximums = new List[3];
			maximums[0] = new ArrayList<Point2D_I16>();
			maximums[1] = new ArrayList<Point2D_I16>();
			maximums[2] = new ArrayList<Point2D_I16>();
		}
		foundPoints.clear();

		// compute feature intensity in each level
		for (int i = 0; i < ss.getNumLayers(); i++) {
			detectCandidateFeatures(ss.getLayer(i), ss.getSigma(i));

			// find maximum in NxNx3 (local image and scale space) region
			if (i >= 2) {
				findLocalScaleSpaceMax(ss, i - 1);
			}
		}
	}