@Override
	public boolean add(final T element) {
		requireNonNull(element);

		boolean updated = false;
		final Iterator<T> iterator = _population.iterator();
		while (iterator.hasNext()) {
			final T existing = iterator.next();

			int cmp = _dominance.compare(element, existing);
			if (cmp > 0) {
				iterator.remove();
				updated = true;
			} else if (cmp < 0 || element.equals(existing)) {
				return updated;
			}
		}

		_population.add(element);
		return true;
	}