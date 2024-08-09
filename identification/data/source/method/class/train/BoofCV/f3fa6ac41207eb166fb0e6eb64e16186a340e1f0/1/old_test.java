@Test
	public void kanji() {
		QrCodeEncoder encoder = new QrCodeEncoder();
		encoder.setVersion(1).setError(QrCode.ErrorLevel.M).
				setMask(QrCodeMaskPattern.M011).
				kanji("阿ん鞠ぷへ≦Ｋ");

		byte expected[] = new byte[]{
				0x01,0x4E,(byte)0x8B,(byte)0xA0,0x23,
				0x2F,(byte)0x83,(byte)0xAA,0x50,0x0D,(byte)0x88,(byte)0x82,0x2B,0x00};

		for (int i = 0; i < expected.length; i++) {
			assertEquals(expected[i],encoder.packed.data[i]);
		}
	}