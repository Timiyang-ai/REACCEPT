@Test
	public void localize_OnePixelModules() {
		QrCode qr = new QrCode();
		qr.version = 2;

		localize(qr, 1);
	}