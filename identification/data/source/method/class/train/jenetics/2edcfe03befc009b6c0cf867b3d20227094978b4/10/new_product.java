public static void setRandom(final Random random) {
		RANDOM.set(nonNull(random, "Random object"));
	}