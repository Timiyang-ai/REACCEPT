@Override
	public synchronized void stop() {
		if (running) {
			running = false;
			for (Exchange exchange : exchangesByMID.values()) {
				exchange.getRequest().setCanceled(true);
			}
			if (statusLogger != null) {
				statusLogger.cancel(false);
				statusLogger = null;
			}
			deduplicator.stop();
			exchangesByMID.clear();
			exchangesByToken.clear();
		}
	}