static Double[] toDouble(Indicator<Decimal> ref, int index, int timeFrame) {

		int end = Math.max(0, index - timeFrame + 1);
		Double[] all = new Double[timeFrame];

		for (int i = index - 1; i >= end; i--) {
			Decimal number = ref.getValue(index);
			all[i] = number.toDouble();
		}

		return all;
	}