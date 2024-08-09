@Test(expected=IllegalArgumentException.class)
	public void testCreateThumbnail_IOSII_Transcoding_Bmp_Gif() throws IOException
	{
		/*
		 * Actual test
		 */
		byte[] bytes = new byte[40054];
		new FileInputStream("test-resources/Thumbnailator/grid.bmp").read(bytes);
		
		InputStream is = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try
		{
			Thumbnailator.createThumbnail(is, os, "gif", 50, 50);
			fail();
		}
		catch (IllegalArgumentException e)
		{
			assertTrue(e.getMessage().contains("gif"));
			throw e;
		}
	}