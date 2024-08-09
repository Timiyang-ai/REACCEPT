public synchronized void dispose () {
		log.debug("Disposing.");
		clear();
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			new GdxRuntimeException("Couldn't shutdown loading thread");
		}
	}