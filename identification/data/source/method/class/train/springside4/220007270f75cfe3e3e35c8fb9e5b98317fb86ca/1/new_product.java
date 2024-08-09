public Timer timer(String name, Double... pcts) {
		if (timers.containsKey(name)) {
			return timers.get(name);
		} else {
			Timer timer = new Timer(pcts);
			return register(timers, name, timer);
		}
	}