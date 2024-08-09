public static void startOpenmrs(final ServletContext servletContext) throws ServletException {
		
		// create a new thread and execute that task in it
		DaemonThread startOpenmrsThread = new DaemonThread() {
			
			@Override
			public void run() {
				isDaemonThread.set(true);
				try {
					Listener.startOpenmrs(servletContext);
				}
				catch (Throwable t) {
					exceptionThrown = t;
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