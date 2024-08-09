@Test
	public void encodeFormatMessage() {

		int found = QrCodePolynomialMath.encodeFormatMessage(QrCode.ErrorCorrectionLevel.M,0b101);
		int expected = 0b100000011001110;

		assertEquals(expected,found);
	}