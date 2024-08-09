@Test
	public void centerOnSquare() {
		QrCode qr = new QrCode();
		qr.version = 2;

		centerOnSquare(qr,4);
		centerOnSquare(qr,1);
	}