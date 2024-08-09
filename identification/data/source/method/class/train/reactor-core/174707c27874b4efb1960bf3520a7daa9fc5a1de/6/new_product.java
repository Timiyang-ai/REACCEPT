public final Mono<@NonNull List<@NonNull T>> collectSortedList(Comparator<? super @NonNull T> comparator,
			int capacityHint) {
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

		Mono<List<T>> merged = railSorted.reduce((a, b) -> sortedMerger(a, b, comparator));

		return merged;
	}