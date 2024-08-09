public void shutDown() {
		synchronized (lock) {
			if (!isShutDown) {
				LOG.info("Shutting down stack trace sample coordinator.");

				for (PendingStackTraceSample pending : pendingSamples.values()) {
					pending.discard(new RuntimeException("Shut down"));
				}

				pendingSamples.clear();

				isShutDown = true;
			}
		}
	}