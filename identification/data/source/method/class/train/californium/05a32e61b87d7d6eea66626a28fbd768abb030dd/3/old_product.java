@Override
	public synchronized void stop() {
		if (running) {
			running = false;
			if (statusLogger != null) {
				statusLogger.cancel(false);
				statusLogger = null;
			}
			deduplicator.stop();
			exchangesByMID.clear();
			exchangesByToken.clear();
		}
	}