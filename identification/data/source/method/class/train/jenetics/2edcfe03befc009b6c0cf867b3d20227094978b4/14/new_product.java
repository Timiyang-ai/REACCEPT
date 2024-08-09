public static void setRandom(final Random random) {
		RANDOM.set(new RandomAccessor(nonNull(random, "Random object")));
	}