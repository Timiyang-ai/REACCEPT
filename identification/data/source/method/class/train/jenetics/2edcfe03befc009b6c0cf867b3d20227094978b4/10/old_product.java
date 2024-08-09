public static void setRandom(final Random random) {
		Validator.nonNull(random, "Random object");
		RANDOM.set(random);
	}