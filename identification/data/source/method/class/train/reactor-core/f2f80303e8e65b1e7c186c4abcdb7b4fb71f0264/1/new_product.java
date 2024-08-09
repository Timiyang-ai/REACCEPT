FluxMergeOrdered<T> mergeAdditionalSource(Publisher<? extends T> source,
			Comparator<? super T> otherComparator) {
		int n = sources.length;
		@SuppressWarnings("unchecked")
		Publisher<? extends T>[] newArray = new Publisher[n + 1];
		System.arraycopy(sources, 0, newArray, 0, n);
		newArray[n] = source;

		if (!valueComparator.equals(otherComparator)) {
			@SuppressWarnings("unchecked")
			Comparator<T> currentComparator = (Comparator<T>) this.valueComparator;
			final Comparator<T> newComparator = currentComparator.thenComparing(otherComparator);
			return new FluxMergeOrdered<>(prefetch, queueSupplier, newComparator, newArray);
		}
		return new FluxMergeOrdered<>(prefetch, queueSupplier, valueComparator, newArray);
	}