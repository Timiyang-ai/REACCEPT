@Test(expected=NullPointerException.class)
	public void scalingMode_Null()
	{
		try
		{
			// given
			// when
			Thumbnails.of("non-existent-file")
				.size(200, 200)
				.scalingMode(null);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Scaling mode is null.", e.getMessage());
			throw e;
		}
	}