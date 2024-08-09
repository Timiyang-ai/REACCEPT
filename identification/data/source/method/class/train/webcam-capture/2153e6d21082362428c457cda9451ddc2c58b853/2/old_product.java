public void lock() {
		LOG.debug("Lock {}", webcam);
		PREFS.putBoolean(webcam.getName(), true);
	}