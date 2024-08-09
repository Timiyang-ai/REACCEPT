@SafeVarargs
	public static <T> ParallelFlux<T> from(Publisher<T>... publishers) {
		return onAssembly(new ParallelArraySource<>(publishers));
	}