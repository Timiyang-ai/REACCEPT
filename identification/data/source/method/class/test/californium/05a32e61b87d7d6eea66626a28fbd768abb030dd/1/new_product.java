@Override
	public synchronized void stop() {
		if (running) {
			if (statusLogger != null) {
				statusLogger.cancel(false);
			}
			if (scheduler != null) {
				scheduler.shutdownNow();
			}
			deduplicator.stop();
			exchangesByMID.clear();
			exchangesByToken.clear();
			running = false;
		}
	}