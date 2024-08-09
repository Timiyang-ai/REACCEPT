	private static <C extends Comparable<C>> int linearindex(final C[] classes, final C value) {
		int index = classes.length;
		for (int i = 0; i < classes.length && index == classes.length; ++i) {
			if (value.compareTo(classes[i]) < 0) {
				index = i;
			}
		}
		return index;
	}