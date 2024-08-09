public static void setRandom(final ThreadLocal<? extends Random> random) {
		requireNonNull(random, "Random must not be null.");
		setEntry(new ThreadLocalRandomSupplier<>(random));
	}