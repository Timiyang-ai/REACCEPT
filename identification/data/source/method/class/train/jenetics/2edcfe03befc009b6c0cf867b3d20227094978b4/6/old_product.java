public static void setRandom(final Random random) {
		RANDOM.set(() -> random);
	}