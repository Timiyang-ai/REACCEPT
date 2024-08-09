protected void StartEventDispatcher() {
		//Create a new single thread executor.
		eventExecutor = Executors.newSingleThreadExecutor();
	}