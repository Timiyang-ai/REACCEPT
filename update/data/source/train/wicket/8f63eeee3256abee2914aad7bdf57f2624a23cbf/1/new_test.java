@Test
	public void testToString()
	{
		assertEquals("1 bytes", Bytes.bytes(1).toString());
		assertEquals("1KB", Bytes.bytes(1024).toString());
		assertEquals("1MB", Bytes.bytes(1024 * 1024L).toString());
		assertEquals("1GB", Bytes.bytes(1024 * 1024 * 1024L).toString());
		assertEquals("1TB", Bytes.bytes(1024 * 1024 * 1024 * 1024L).toString());
		assertEquals("1.5KB", Bytes.bytes(1024 * 1.5).toString());

		assertEquals("1 bytes", Bytes.bytes(1).toString(Locale.GERMAN));
	}