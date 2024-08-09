public boolean open(boolean async) {

		if (open.compareAndSet(false, true)) {

			assert updater != null;
			assert lock != null;

			// lock webcam for other Java (only) processes

			lock.lock();

			// open webcam device

			WebcamOpenTask task = new WebcamOpenTask(driver, device);
			try {
				task.open();
			} catch (InterruptedException e) {
				lock.unlock();
				open.set(false);
				LOG.debug("Thread has been interrupted in the middle of webcam opening process!", e);
				return false;
			} catch (WebcamException e) {
				lock.unlock();
				open.set(false);
				throw e;
			}

			LOG.debug("Webcam is now open {}", getName());

			// setup non-blocking configuration

			asynchronous = async;

			if (async) {
				updater.start();
			}

			// install shutdown hook

			Runtime.getRuntime().addShutdownHook(hook = new WebcamShutdownHook(this));

			// notify listeners

			WebcamEvent we = new WebcamEvent(WebcamEventType.OPEN, this);
			Iterator<WebcamListener> wli = listeners.iterator();
			WebcamListener l = null;

			while (wli.hasNext()) {
				l = wli.next();
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