public boolean open(boolean async) {

		if (open.compareAndSet(false, true)) {

			WebcamOpenTask task = new WebcamOpenTask(driver, device);
			try {
				task.open();
			} catch (InterruptedException e) {
				open.set(false);
				LOG.error("Processor has been interrupted before webcam was open!", e);
				return false;
			} catch (WebcamException e) {
				open.set(false);
				throw e;
			}

			LOG.debug("Webcam is now open {}", getName());

			// setup non-blocking configuration

			asynchronous = async;

			if (async) {
				if (updater == null) {
					updater = new WebcamUpdater(this);
				}
				updater.start();
			}

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

		} else {
			LOG.debug("Webcam is already open {}", getName());
		}

		return true;
	}