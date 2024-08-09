public synchronized void open() {

		if (open) {
			return;
		}

		if (LOG.isInfoEnabled()) {
			LOG.info("Opening webcam " + getName());
		}

		if (device.getSize() == null) {
			device.setSize(device.getSizes()[0]);
		}

		device.open();
		open = true;

		hook = new ShutdownHook(this);

		Runtime.getRuntime().addShutdownHook(hook);

		WebcamEvent we = new WebcamEvent(this);
		for (WebcamListener l : listeners) {
			try {
				l.webcamOpen(we);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}