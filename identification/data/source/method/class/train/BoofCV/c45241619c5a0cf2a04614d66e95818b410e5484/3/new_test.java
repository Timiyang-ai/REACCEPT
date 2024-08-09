@Test
	public void numeric_specification() {
		QrCode qr = new QrCodeEncoder().setVersion(1).
				setError(QrCode.ErrorLevel.M).
				setMask(new QrCodeMaskPattern.NONE(0b011)).
				addNumeric("01234567").fixate();

		byte[] expected = new byte[]{0b00010000,
		0b00100000, 0b00001100, 0b01010110, 0b01100001 ,(byte)0b10000000, (byte)0b11101100, 0b00010001,
				(byte)0b11101100, 0b00010001, (byte)0b11101100, 0b00010001, (byte)0b11101100, 0b00010001,
				(byte)0b11101100, 0b00010001, (byte)0b10100101, 0b00100100,
				(byte)0b11010100, (byte)0b11000001, (byte)0b11101101, 0b00110110,
				(byte)0b11000111, (byte)0b10000111, 0b00101100, 0b01010101};

		QrCodeEncoder.flipBits8(expected,expected.length);

		assertEquals(qr.rawbits.length,expected.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i],qr.rawbits[i]);
		}
	}