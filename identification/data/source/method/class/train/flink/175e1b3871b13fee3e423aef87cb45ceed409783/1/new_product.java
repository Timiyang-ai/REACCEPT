public void clearAll() {
		synchronized (registeredHandlers) {
			registeredHandlers.clear();
		}
	}