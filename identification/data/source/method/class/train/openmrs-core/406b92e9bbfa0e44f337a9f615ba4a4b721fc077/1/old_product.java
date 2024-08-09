public static Thread runInNewDaemonThread(final Runnable runnable) {
		// make sure we're already in a daemon thread
		if (!isDaemonThread())
			throw new APIAuthenticationException("Can only be called from a Daemon thread");
		
		// we should consider making DaemonThread public, so the caller can access returnedObject and exceptionThrown
		DaemonThread thread = new DaemonThread() {
			
			@Override
			public void run() {
				isDaemonThread.set(true);
				try {
					Context.openSession();
					runnable.run();
				}
				finally {
					Context.closeSession();
				}
			}
		};
		
		thread.start();
		return thread;
	}