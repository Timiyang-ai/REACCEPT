public Counter counter(String name) {
		if (metrics.containsKey(name)) {
			return (Counter) metrics.get(name);
		} else {
			Counter counter = new Counter();
			return (Counter) register(name, counter);
		}
	}