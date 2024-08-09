@SuppressWarnings("unchecked")
	public static void setRandom(final ThreadLocal<? extends Random> random) {
		requireNonNull(random, "Random must not be null.");
		CONTEXT.set((Supplier<Random>)new ThreadLocalRandomSupplier<>(random));
	}