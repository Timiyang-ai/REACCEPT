public static void sleep(long millis) throws InterruptedException {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < millis) {
			// Do something that yields the thread!
		}
	}