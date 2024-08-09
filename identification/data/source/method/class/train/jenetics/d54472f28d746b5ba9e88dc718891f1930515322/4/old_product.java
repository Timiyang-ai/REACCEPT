public ParetoFront<T> trim(
		final int size,
		final ElementComparator<? super T> comparator,
		final ElementDistance<? super T> distance,
		final ToIntFunction<? super T> dimension
	) {
		requireNonNull(comparator);
		requireNonNull(distance);
		requireNonNull(dimension);

		if (size() > size) {
			final double[] distances = Pareto.crowdingDistance(
				SeqView.of(_population),
				comparator,
				distance,
				dimension
			);

			final int[] idx = IndexSorter.sort(distances);

			final List<T> list = new ArrayList<>(size);
			for (int i = 0; i < size; ++i) {
				list.add(_population.get(idx[i]));
			}

			_population.clear();
			_population.addAll(list);
		}

		return this;
	}