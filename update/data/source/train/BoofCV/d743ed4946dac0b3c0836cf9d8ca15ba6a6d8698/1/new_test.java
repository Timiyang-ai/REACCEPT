@Test
	public void centerOnSquare() {
		QrCode qr = new QrCodeEncoder().setVersion(2).numeric("12340324").fixate();

		centerOnSquare(qr,4);
		centerOnSquare(qr,1);
	}