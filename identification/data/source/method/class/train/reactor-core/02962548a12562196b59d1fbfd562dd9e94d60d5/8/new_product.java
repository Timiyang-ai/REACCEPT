public static <T> ParallelFlux<T> from(Publisher<? extends T> source) {
		return from(source,
				Runtime.getRuntime()
				       .availableProcessors(), ReactorProperties.SMALL_BUFFER_SIZE,
				QueueSupplier.small());
	}