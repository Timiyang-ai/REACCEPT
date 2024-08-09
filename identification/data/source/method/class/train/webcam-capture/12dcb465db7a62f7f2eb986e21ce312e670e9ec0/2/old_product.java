public synchronized void open() {

		if (open) {
			LOG.debug("Webcam has already been open {}", getName());
			return;
		}

		LOG.info("Opening webcam {}", getName());

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
				LOG.error(String.format("Notify webcam open, exception when calling listener %s", l.getClass()), e);
			}
		}
	}