public synchronized void dispose () {
		log.debug("Disposing.");
		clear();
		executor.dispose();
	}