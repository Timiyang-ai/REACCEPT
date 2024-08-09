protected void unregisterAllTimeouts() {
		for (Timeout<K> timeout : timeouts.values()) {
			if (timeout != null) {
				timeout.cancel();
			}
		}
		timeouts.clear();
	}