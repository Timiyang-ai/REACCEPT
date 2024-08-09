public synchronized void open() {

		device.open();
		open = true;

		WebcamEvent we = new WebcamEvent(this);
		for (WebcamListener l : listeners) {
			try {
				l.webcamOpen(we);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}