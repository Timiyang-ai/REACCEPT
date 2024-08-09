static Double[] toDouble(Indicator<Num> ref, int index, int barCount) {

		Double[] all = new Double[barCount];

		int startIndex = Math.max(0, index - barCount + 1);
		for (int i = 0; i < barCount; i++) {
			Num number = ref.getValue(i + startIndex);
			all[i] = number.doubleValue();
		}

		return all;
	}