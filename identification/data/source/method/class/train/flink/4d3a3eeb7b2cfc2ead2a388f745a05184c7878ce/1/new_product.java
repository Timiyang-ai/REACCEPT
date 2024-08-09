protected void unregisterAllTimeouts() {
		for (Timeout<K> timeout : timeouts.values()) {
			timeout.cancel();
		}
		timeouts.clear();
	}