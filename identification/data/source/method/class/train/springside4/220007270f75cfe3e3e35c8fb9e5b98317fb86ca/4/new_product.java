public Execution execution(String name, Double... pcts) {
		if (executions.containsKey(name)) {
			return executions.get(name);
		} else {
			Execution execution = new Execution(pcts);
			return register(executions, name, execution);
		}
	}