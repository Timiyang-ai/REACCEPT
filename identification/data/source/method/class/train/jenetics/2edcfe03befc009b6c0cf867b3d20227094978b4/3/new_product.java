public static void setRandom(final Random random) {
		Checker.checkNull(random, "Random object");
		RANDOM.set(random);
	}