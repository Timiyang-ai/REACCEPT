protected void startEventDispatcher() {
		//Create a new single thread executor.
		eventExecutor = Executors.newSingleThreadExecutor();
	}