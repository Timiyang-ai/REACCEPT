public static <T> ParallelFlux<T> from(Publisher<? extends T> source) {
		return from(source,
				Runtime.getRuntime()
				       .availableProcessors(), Queues.SMALL_BUFFER_SIZE,
				Queues.small());
	}