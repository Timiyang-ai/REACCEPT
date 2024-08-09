FluxMergeOrdered<T> mergeAdditionalSource(Publisher<? extends T> source) {
		int n = sources.length;
		@SuppressWarnings("unchecked")
		Publisher<? extends T>[] newArray = new Publisher[n + 1];
		System.arraycopy(sources, 0, newArray, 0, n);
		newArray[n] = source;
		return new FluxMergeOrdered<>(prefetch, queueSupplier, valueComparator, newArray);
	}