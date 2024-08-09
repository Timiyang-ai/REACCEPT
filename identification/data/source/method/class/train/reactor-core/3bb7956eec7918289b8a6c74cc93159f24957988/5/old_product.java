public final Flux<T> sorted(Comparator<? super T> comparator, int capacityHint) {
		int ch = capacityHint / parallelism() + 1;
		ParallelFlux<List<T>> railReduced =
				reduce(() -> new ArrayList<>(ch), (a, b) -> {
					a.add(b);
					return a;
				});
		ParallelFlux<List<T>> railSorted = railReduced.map(list -> {
			list.sort(comparator);
			return list;
		});

		Flux<T> merged = new ParallelSortedJoin<>(railSorted, comparator);

		return merged;
	}