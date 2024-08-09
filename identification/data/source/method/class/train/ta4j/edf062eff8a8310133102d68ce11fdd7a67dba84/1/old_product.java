static Double[] toDouble(Indicator<Num> ref, int index, int timeFrame) {

		Double[] all = new Double[timeFrame];

		for (int i = Math.max(0, index - timeFrame + 1); i <= index; i++) {
			Num number = ref.getValue(index);
			all[i] = number.doubleValue();
		}

		return all;
	}