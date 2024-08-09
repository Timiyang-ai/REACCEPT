@Test
	public void testIsSecure() {

		RawData rawData = RawData.inbound(new byte[]{0x01, 0x02}, getSecureEndpointContext(), false);
		assertTrue(rawData.isSecure());

		rawData = RawData.inbound(new byte[]{0x01, 0x02}, getNonSecureEndpointContext(), false);
		assertFalse(rawData.isSecure());
	}