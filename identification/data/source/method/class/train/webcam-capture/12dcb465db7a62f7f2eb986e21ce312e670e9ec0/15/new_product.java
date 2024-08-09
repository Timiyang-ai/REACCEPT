public synchronized void open() {

		if (device.getSize() == null) {
			device.setSize(device.getSizes()[0]);
		}

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