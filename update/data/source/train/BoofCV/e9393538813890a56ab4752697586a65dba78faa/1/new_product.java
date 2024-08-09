@SuppressWarnings({"ForLoopReplaceableByForEach"})
	protected boolean selectMatchSet(List<Point> dataSet, double threshold, int minSize,
									 Model param) {
		candidatePoints.clear();
		modelDistance.setModel(param);
		for (int i = 0; i < dataSet.size(); i++) {
			Point point = dataSet.get(i);

			double distance = modelDistance.computeDistance(point);
			if (distance < threshold) {
				candidatePoints.add(point);
			}
		}

		return candidatePoints.size() >= minSize;
	}