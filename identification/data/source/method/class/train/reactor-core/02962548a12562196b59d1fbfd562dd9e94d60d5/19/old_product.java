public static <T> ParallelFlux<@NonNull T> from(Publisher<? extends @NonNull T> source,
			int parallelism,
			int prefetch,
			Supplier<Queue<T>> queueSupplier) {
		Objects.requireNonNull(queueSupplier, "queueSupplier");
		Objects.requireNonNull(source, "source");

		return onAssembly(new ParallelSource<>(source,
				parallelism,
				prefetch, queueSupplier));
	}