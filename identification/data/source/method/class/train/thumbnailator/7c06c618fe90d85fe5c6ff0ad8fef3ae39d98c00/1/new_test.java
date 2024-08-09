	@Test
	public void isSupportedOutputFormat_SupportedFormat()
	{
		// given a supported format
		String format = "JPEG";
		
		// when
		boolean isSupported = ThumbnailatorUtils.isSupportedOutputFormat(format);
		
		// then, it is supported.
		assertTrue(isSupported);
	}