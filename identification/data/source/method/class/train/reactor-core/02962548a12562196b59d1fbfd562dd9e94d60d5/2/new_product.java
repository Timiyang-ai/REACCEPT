public static <T> ParallelFlux<T> from(Publisher<? extends T> source,
			int parallelism,
			int prefetch,
			Supplier<Queue<T>> queueSupplier) {
		Objects.requireNonNull(queueSupplier, "queueSupplier");
		Objects.requireNonNull(source, "source");

		return onAssembly(new ParallelSource<>(source,
				parallelism,
				prefetch, queueSupplier));
	}