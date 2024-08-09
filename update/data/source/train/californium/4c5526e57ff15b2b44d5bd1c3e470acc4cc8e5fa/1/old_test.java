@Test
	public void testIsSecure() {

		RawData rawData = RawData.inbound(new byte[]{0x01, 0x02}, SOURCE, null, getSecureCorrelationContext(), false);
		assertTrue(rawData.isSecure());

		rawData = RawData.inbound(new byte[]{0x01, 0x02}, SOURCE, null, getNonSecureCorrelationContext(), false);
		assertFalse(rawData.isSecure());

		rawData = RawData.inbound(new byte[]{0x01, 0x02}, SOURCE, null, null, false);
		assertFalse(rawData.isSecure());
	}