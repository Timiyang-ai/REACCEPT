public void open() {

		if (!open.compareAndSet(false, true)) {
			LOG.debug("Webcam is already open {}", getName());
			return;
		}

		WebcamOpenTask task = new WebcamOpenTask(this);
		task.open(this);

		Runtime.getRuntime().addShutdownHook(hook = new ShutdownHook(this));
	}