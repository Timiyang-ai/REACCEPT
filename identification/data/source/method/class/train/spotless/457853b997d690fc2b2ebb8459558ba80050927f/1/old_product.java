public boolean misbehaved() {
		boolean isWellBehaved = type == ResultType.CONVERGE && steps.size() <= 1;
		return !isWellBehaved;
	}