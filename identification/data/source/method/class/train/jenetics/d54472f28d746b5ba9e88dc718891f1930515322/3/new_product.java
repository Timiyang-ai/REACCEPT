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
				Seq.viewOf(_population),
				comparator,
				distance,
				dimension
			);
			final ProxySorter sorter = ProxySorter.sorter(distances.length);
			final int[] indexes = sorter.sort(distances);
			revert(indexes);

			final List<T> list = IntStream.of(indexes)
				.limit(size)
				.mapToObj(_population::get)
				.collect(Collectors.toList());

			_population.clear();
			_population.addAll(list);
		}

		return this;
	}