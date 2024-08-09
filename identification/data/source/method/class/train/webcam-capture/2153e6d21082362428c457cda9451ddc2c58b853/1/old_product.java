public void lock() {

		if (isLocked()) {
			throw new WebcamException(String.format("Webcam %s has been locked and cannot be open", webcam.getName()));
		}

		if (!locked.compareAndSet(false, true)) {
			return;
		}

		LOG.debug("Lock {}", webcam);

		update();

		updater = new LockUpdater();
		updater.start();
	}