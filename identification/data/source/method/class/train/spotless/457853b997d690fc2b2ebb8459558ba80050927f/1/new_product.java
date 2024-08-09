public boolean misbehaved() {
		boolean isWellBehaved = type == Type.CONVERGE && steps.size() <= 1;
		return !isWellBehaved;
	}