public boolean sameState(final MinMax<C> other) {
		return Objects.equals(_min, other._min) &&
			Objects.equals(_max, other._max);
	}