@SuppressWarnings("squid:S1217")
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
					//Suppressing sonar issue "squid:S1217"
					//We intentionally do not start a new thread yet, rather wrap the run call in a session.
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