public void lock() {

		if (isLocked()) {
			throw new WebcamLockException(String.format("Webcam %s has already been locked", webcam.getName()));
		}

		if (!locked.compareAndSet(false, true)) {
			return;
		}

		LOG.debug("Lock {}", webcam);

		update();

		updater = new LockUpdater();
		updater.start();
	}