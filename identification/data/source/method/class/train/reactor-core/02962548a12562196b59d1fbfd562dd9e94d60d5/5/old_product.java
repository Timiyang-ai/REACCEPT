@SafeVarargs
	public static <T> ParallelFlux<@NonNull T> from(Publisher<T> @NonNull ... publishers) {
		return onAssembly(new ParallelArraySource<>(publishers));
	}