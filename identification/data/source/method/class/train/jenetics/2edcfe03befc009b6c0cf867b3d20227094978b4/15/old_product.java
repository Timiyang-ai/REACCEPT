public static void setRandom(final ThreadLocal<? extends Random> random) {
		RANDOM.set(random::get);
	}