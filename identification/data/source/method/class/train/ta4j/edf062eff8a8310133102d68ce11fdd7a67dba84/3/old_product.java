static Double[] toDouble(Indicator<Num> ref, int index, int barCount) {

		Double[] all = new Double[barCount];

		for (int i = Math.max(0, index - barCount + 1); i <= index; i++) {
			Num number = ref.getValue(index);
			all[i] = number.doubleValue();
		}

		return all;
	}