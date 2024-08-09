@SafeVarargs
	public static <T> ParallelFlux<T> from(Publisher<T>... publishers) {
		if (publishers.length == 0) {
			throw new IllegalArgumentException("Zero publishers not supported");
		}
		return new ParallelUnorderedFrom<>(publishers);
	}