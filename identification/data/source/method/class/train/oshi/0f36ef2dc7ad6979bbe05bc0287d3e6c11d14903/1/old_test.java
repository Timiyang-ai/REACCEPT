@Test
	public void testFormatBytes() {
		assertEquals("0 bytes", FormatUtil.formatBytes(0));
		assertEquals("1 byte", FormatUtil.formatBytes(1));
		assertEquals("532 bytes", FormatUtil.formatBytes(532));
		assertEquals("1 KB", FormatUtil.formatBytes(1024));
		assertEquals("1 GB", FormatUtil.formatBytes(1024 * 1024 * 1024));
		assertEquals("1 TiB", FormatUtil.formatBytes(1099511627776L));
	}