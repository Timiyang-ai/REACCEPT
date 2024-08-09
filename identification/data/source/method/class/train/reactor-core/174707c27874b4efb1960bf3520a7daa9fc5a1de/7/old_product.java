public final Mono<List<T>> collectSortedList(Comparator<? super T> comparator,
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

		Mono<List<T>> merged = railSorted.reduce((a, b) -> {
			int n = a.size() + b.size();
			if (n == 0) {
				return new ArrayList<>();
			}
			List<T> both = new ArrayList<>(n);

			Iterator<T> at = a.iterator();
			Iterator<T> bt = b.iterator();

			T s1 = at.hasNext() ? at.next() : null;
			T s2 = bt.hasNext() ? bt.next() : null;

			while (s1 != null && s2 != null) {
				if (comparator.compare(s1, s2) < 0) { // s1 comes before s2
					both.add(s1);
					s1 = at.hasNext() ? at.next() : null;
				}
				else {
					both.add(s2);
					s2 = bt.hasNext() ? bt.next() : null;
				}
			}

			if (s1 != null) {
				both.add(s1);
				while (at.hasNext()) {
					both.add(at.next());
				}
			}
			else if (s2 != null) {
				both.add(s2);
				while (bt.hasNext()) {
					both.add(bt.next());
				}
			}

			return both;
		});

		return merged;
	}