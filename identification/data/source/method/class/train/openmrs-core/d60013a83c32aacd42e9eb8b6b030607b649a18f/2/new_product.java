public static void startOpenmrs(final ServletContext servletContext) throws DatabaseUpdateException,
	        InputRequiredException {
		
		// create a new thread and start openmrs in it.
		DaemonThread startOpenmrsThread = new DaemonThread() {
			
			@Override
			public void run() {
				isDaemonThread.set(true);
				try {
					Listener.startOpenmrs(servletContext);
				}
				catch (Exception e) {
					exceptionThrown = e;
				}
				finally {
					Context.closeSession();
				}
			}
		};
		
		startOpenmrsThread.start();
		
		// wait for the "startOpenmrs" thread to finish
		try {
			startOpenmrsThread.join();
		}
		catch (InterruptedException e) {
			// ignore
		}
		
		if (startOpenmrsThread.getExceptionThrown() != null) {
			throw new ModuleException("Unable to start OpenMRS. Error thrown was: "
			        + startOpenmrsThread.getExceptionThrown().getMessage(), startOpenmrsThread.getExceptionThrown());
		}
	}