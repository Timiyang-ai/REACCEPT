@Override
	public void initEncoder(int w, double minVal, double maxVal, int n,
			double radius, double resolution) {
		this.setPeriodic(false);
		this.encLearningEnabled = true;
		if (this.periodic) {
			throw new IllegalStateException(
					"Adaptive scalar encoder does not encode periodic inputs");
		}
		assert n != 0;
		super.initEncoder(w, minVal, maxVal, n, radius, resolution);
	}