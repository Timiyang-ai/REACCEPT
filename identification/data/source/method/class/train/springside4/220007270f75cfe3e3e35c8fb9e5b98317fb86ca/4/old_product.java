public Execution execution(String name, Double... pcts) {
		if (metrics.containsKey(name)) {
			return (Execution) metrics.get(name);
		} else {
			Execution execution = new Execution(pcts);
			return (Execution) register(name, execution);
		}
	}