@Test
	public void encodeFormatBits() {

		int found = QrCodePolynomialMath.encodeFormatBits(QrCode.ErrorLevel.M,0b101);
		found ^= QrCodePolynomialMath.FORMAT_MASK;
		int expected = 0b100000011001110;

		assertEquals(expected,found);
	}