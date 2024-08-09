@Override
	public synchronized void stop() {
		if (running) {
			if (statusLogger != null) {
				statusLogger.cancel(false);
			}
			deduplicator.stop();
			exchangesByMID.clear();
			exchangesByToken.clear();
			ongoingExchanges.clear();
			running = false;
		}
	}