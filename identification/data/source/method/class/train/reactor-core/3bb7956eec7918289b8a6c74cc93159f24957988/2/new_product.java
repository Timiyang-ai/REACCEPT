public final Flux<T> sorted(Comparator<? super T> comparator) {
		return sorted(comparator, 16);
	}