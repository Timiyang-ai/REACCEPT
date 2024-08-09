	@Test(expected=NullPointerException.class)
	public void write_NullImage() throws IOException
	{
		// given
		File f = new File(TMPDIR, "test.png");
		f.deleteOnExit();

		BufferedImage img = null;
		
		FileImageSink sink = new FileImageSink(f);
		sink.setOutputFormatName("png");
		
		try
		{
			// when
			sink.write(img);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Cannot write a null image.", e.getMessage());
			throw e;
		}
	}