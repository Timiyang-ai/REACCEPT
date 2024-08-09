public void open(boolean async) {

		if (asynchronous.compareAndSet(false, true)) {
			// TODO: start async thread
		}

		if (open.compareAndSet(false, true)) {

			WebcamOpenTask task = new WebcamOpenTask(driver, device);
			task.open();

			LOG.debug("Webcam is now open {}", getName());

			// install shutdown hook

			Runtime.getRuntime().addShutdownHook(hook = new WebcamShutdownHook(this));

			// notify listeners

			WebcamEvent we = new WebcamEvent(this);
			for (WebcamListener l : getWebcamListeners()) {
				try {
					l.webcamOpen(we);
				} catch (Exception e) {
					LOG.error(String.format("Notify webcam open, exception when calling listener %s", l.getClass()), e);
				}
			}

			return;
		}

		LOG.debug("Webcam is already open {}", getName());
	}