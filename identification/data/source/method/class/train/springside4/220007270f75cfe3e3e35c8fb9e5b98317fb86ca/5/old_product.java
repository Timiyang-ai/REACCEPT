public Histogram histogram(String name, Double... pcts) {
		if (metrics.containsKey(name)) {
			return (Histogram) metrics.get(name);
		} else {
			Histogram histogram = new Histogram(pcts);
			return (Histogram) register(name, histogram);
		}
	}