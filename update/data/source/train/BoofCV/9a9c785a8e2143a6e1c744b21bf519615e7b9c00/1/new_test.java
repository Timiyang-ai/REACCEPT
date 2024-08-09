@Test
	public void bitPolyDivide() {
		int message = 0b00101 << 10;
		int divisor = 0b10100110111;

		int found = QrCodePolynomialMath.bitPolyModulus(message,divisor,15,5);
		int expected = 0b0011011100;

		assertEquals(expected,found);
	}