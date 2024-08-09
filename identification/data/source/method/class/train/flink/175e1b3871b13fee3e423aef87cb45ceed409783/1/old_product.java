public void clearAll() {
		synchronized (registeredWriters) {
			registeredWriters.clear();
		}
	}