@SafeVarargs
	public final void addAll(final T... values) {
		addAll(values, 0, values.length);
	}