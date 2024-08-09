@Test
	public void alphanumeric_specification() {
		QrCodeEncoder encoder = new QrCodeEncoder();
		encoder.setVersion(1).
				setError(QrCode.ErrorLevel.H).
				setMask(new QrCodeMaskPattern.NONE(0b011)).
				alphanumeric("AC-42").fixate();

		byte[] expected = new byte[]{0b00100000, 0b00101001, (byte)0b11001110, (byte)0b11100111, 0b00100001,0};

		QrCodeEncoder.flipBits8(expected,expected.length);

		assertEquals(encoder.packed.size/8,expected.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i],encoder.packed.data[i]);
		}
	}