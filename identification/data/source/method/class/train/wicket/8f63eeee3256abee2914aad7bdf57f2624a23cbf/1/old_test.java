@Test
	public void testToString()
	{
		assertEquals("1 bytes", Bytes.bytes(1).toString());
		assertEquals("1K", Bytes.bytes(1024).toString());
		assertEquals("1M", Bytes.bytes(1024 * 1024L).toString());
		assertEquals("1G", Bytes.bytes(1024 * 1024 * 1024L).toString());
		assertEquals("1T", Bytes.bytes(1024 * 1024 * 1024 * 1024L).toString());
		assertEquals("1.5K", Bytes.bytes(1024 * 1.5).toString());

		assertEquals("1 bytes", Bytes.bytes(1).toString(Locale.GERMAN));
	}