public synchronized void open() {

		if (open) {
			return;
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("Opening webcam " + getName());
		}

		ensureSize();

		device.open();
		open = true;

		hook = new ShutdownHook(this);

		Runtime.getRuntime().addShutdownHook(hook);

		WebcamEvent we = new WebcamEvent(this);
		for (WebcamListener l : listeners) {
			try {
				l.webcamOpen(we);
			} catch (Exception e) {
				LOG.error("Notify webcam open, exception when calling {}", WebcamListener.class.getSimpleName(), e);
			}
		}
	}