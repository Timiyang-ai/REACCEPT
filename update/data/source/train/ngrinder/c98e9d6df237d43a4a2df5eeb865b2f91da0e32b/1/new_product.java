@SuppressWarnings("deprecation")
	public static void stopQuietly(Thread thread, String stopMessage) {
		if (thread == null) {
			return;
		}
		// Wait 5000 second for natural death.
		try {
			thread.join(THREAD_WAITING_TIME);
		} catch (Exception e) {
			// Fall through
			noOp();
		}
		try {
			thread.interrupt();
		} catch (Exception e) {
			noOp();
		}
		try {
			// Again Wait 5000 second.
			thread.join(RETRY_MILLISECOND);
		} catch (Exception e) {
			// Fall through
			noOp();
		}
		try {
			// Force to Stop
			if (thread.isAlive()) {
				LOGGER.error(stopMessage);
				thread.stop();
			}
		} catch (Exception e) {
			// Fall through
			noOp();
		}
	}