@SafeVarargs
	final void addAll(final T... values) {
		add(values, 0, values.length);
	}