public static void executeScheduledTask(final Task task) throws Throwable {
		
		// quick check to make sure we're only being called by ourselves
		//Class<?> callerClass = Reflection.getCallerClass(0);
		Class<?> callerClass = new OpenmrsSecurityManager().getCallerClass(0);
		if (!TimerSchedulerTask.class.isAssignableFrom(callerClass))
			throw new APIException("This method can only be called from the TimerSchedulerTask class, not "
			        + callerClass.getName());
		
		// now create a new thread and execute that task in it
		DaemonThread executeTaskThread = new DaemonThread() {
			
			@Override
			public void run() {
				isDaemonThread.set(true);
				
				try {
					Context.openSession();
					TimerSchedulerTask.execute(task);
				}
				catch (Exception e) {
					exceptionThrown = e;
				}
				finally {
					Context.closeSession();
				}
				
			}
		};
		
		executeTaskThread.start();
		
		// wait for the "executeTaskThread" thread to finish
		try {
			executeTaskThread.join();
		}
		catch (InterruptedException e) {
			// ignore
		}
		
		if (executeTaskThread.exceptionThrown != null)
			throw executeTaskThread.exceptionThrown;
		
	}