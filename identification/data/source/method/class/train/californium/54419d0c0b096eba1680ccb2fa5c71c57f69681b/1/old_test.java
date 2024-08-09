@Test
	public void testIsSecure() {

		RawData rawData = RawData.inbound(new byte[]{0x01, 0x02}, SOURCE, null, getSecureEndpointContext(), false);
		assertTrue(rawData.isSecure());

		rawData = RawData.inbound(new byte[]{0x01, 0x02}, SOURCE, null, getNonSecureEndpointContext(), false);
		assertFalse(rawData.isSecure());

		rawData = RawData.inbound(new byte[]{0x01, 0x02}, SOURCE, null, null, false);
		assertFalse(rawData.isSecure());
	}