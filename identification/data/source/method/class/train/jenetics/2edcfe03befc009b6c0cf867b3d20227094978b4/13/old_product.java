public static void setRandom(final Random random) {
		Validator.notNull(random, "Random object");
		RANDOM.set(random);
	}