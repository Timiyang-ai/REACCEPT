@SuppressWarnings({"ForLoopReplaceableByForEach"})
	protected boolean selectMatchSet(List<T> dataSet, double threshold, int minSize,
									 double[] param) {
		candidatePoints.clear();
		modelDistance.setParameters(param);
		for (int i = 0; i < dataSet.size(); i++) {
			T point = dataSet.get(i);

			double distance = modelDistance.computeDistance(point);
			if (distance < threshold) {
				candidatePoints.add(point);
			}
		}

		return candidatePoints.size() >= minSize;
	}