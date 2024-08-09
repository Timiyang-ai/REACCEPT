public static Thread runInNewDaemonThread(final Runnable runnable) {
		// make sure we're already in a daemon thread
		if (!isDaemonThread()) {
			throw new APIAuthenticationException("Only daemon threads can spawn new daemon threads");
		}
		
		// we should consider making DaemonThread public, so the caller can access returnedObject and exceptionThrown
		DaemonThread thread = new DaemonThread() {
			
			@Override
			public void run() {
				isDaemonThread.set(true);
				try {
					Context.openSession();
                    new Thread(runnable).start();
				}
				finally {
					Context.closeSession();
				}
			}
		};
		
		thread.start();
		return thread;
	}