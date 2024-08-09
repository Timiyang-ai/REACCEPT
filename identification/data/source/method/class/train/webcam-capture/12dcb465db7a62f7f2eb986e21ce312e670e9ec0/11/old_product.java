public synchronized void open() {

		if (isOpen()) {
			return;
		}

		locator = device.getLocator();
		format = getVideoFormat(device);
		converter = new BufferToImage(format);

		try {
			player = Manager.createRealizedPlayer(locator);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		control = (FormatControl) getControl(FORMAT_CTRL);
		grabber = (FrameGrabbingControl) getControl(GRABBING_CTRL);

		if (control.setFormat(format) == null) {
			throw new RuntimeException("Cannot change video format");
		}

		starter = new PlayerStarter(player);
		starter.start();

		try {
			semaphore.acquire(2);
			starter.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		open = started && available;

		WebcamEvent we = new WebcamEvent(this);

		synchronized (listeners) {
			Iterator<WebcamListener> wli = listeners.iterator();
			while (wli.hasNext()) {
				WebcamListener l = wli.next();
				try {
					l.webcamOpen(we);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}