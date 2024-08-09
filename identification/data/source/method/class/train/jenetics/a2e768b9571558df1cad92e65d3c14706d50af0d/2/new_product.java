public boolean sameState(final IntMomentStatistics other) {
		return this == other ||
			_min == other._min &&
			_max == other._max &&
			_sum == other._sum &&
			super.sameState(other);
	}