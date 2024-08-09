public static void setRandom(final Random random) {
		nonNull(random, "Random object");
		RANDOM.set(new Reference<Random>() {
			@Override public Random get() {
				return random;
			}
			@Override public void set(Random random) {
			}
		});
	}