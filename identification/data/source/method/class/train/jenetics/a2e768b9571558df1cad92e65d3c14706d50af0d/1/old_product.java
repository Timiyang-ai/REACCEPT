public boolean sameState(final DoubleMomentStatistics other) {
		return Double.compare(_min, other._min) == 0 &&
			Double.compare(_max, other._max) == 0 &&
			_sum.sameState(other._sum) &&
			super.sameState(other);
	}