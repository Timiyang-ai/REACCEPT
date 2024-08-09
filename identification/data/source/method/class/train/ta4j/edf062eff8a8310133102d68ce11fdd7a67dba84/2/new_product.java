static Double[] toDouble(Indicator<Decimal> ref, int index, int timeFrame) {
		
		Double[] all = new Double[timeFrame];

		for (int i = Math.max(0, index - timeFrame + 1); i <= index; i++) {
			Decimal number = ref.getValue(index);
			all[i] = number.toDouble();
		}

		return all;
	}