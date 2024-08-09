public Histogram histogram(String name, Double... pcts) {
		if (histograms.containsKey(name)) {
			return histograms.get(name);
		} else {
			Histogram histogram = new Histogram(((pcts != null) && (pcts.length > 0)) ? pcts : defaultPcts);
			return register(histograms, name, histogram);
		}
	}