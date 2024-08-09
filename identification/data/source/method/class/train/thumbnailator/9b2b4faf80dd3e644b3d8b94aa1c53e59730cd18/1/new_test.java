	@Test(expected=NullPointerException.class)
	public void write_NullImage() throws IOException
	{
		// given
		OutputStream os = mock(OutputStream.class);

		BufferedImage img = null;
		
		OutputStreamImageSink sink = new OutputStreamImageSink(os);
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