public static <T> ParallelFlux<T> from(Publisher<? extends T> source,
			int parallelism,
			int prefetch,
			Supplier<Queue<T>> queueSupplier) {
		if (parallelism <= 0) {
			throw new IllegalArgumentException("parallelism > 0 required but it was " + parallelism);
		}
		if (prefetch <= 0) {
			throw new IllegalArgumentException("prefetch > 0 required but it was " + prefetch);
		}

		Objects.requireNonNull(queueSupplier, "queueSupplier");
		Objects.requireNonNull(source, "queueSupplier");

		return onAssembly(new ParallelUnorderedSource<>(source,
				parallelism,
				prefetch, queueSupplier));
	}