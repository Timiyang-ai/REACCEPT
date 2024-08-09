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

			final List<T> list = IntStream.of(IndexSorter.sort(distances))
				.limit(size)
				.mapToObj(_population::get)
				.collect(Collectors.toList());

			_population.clear();
			_population.addAll(list);
		}

		return this;
	}