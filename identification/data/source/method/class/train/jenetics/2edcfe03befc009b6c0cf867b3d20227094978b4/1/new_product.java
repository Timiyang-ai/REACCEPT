public static void setRandom(final Random random) {
		requireNonNull(random, "Random must not be null.");
		setEntry(new RandomSupplier<>(random));
	}