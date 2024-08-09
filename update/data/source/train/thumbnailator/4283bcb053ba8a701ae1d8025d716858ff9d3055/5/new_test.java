@Test(expected=NullPointerException.class)
	public void alphaInterpolation_Null()
	{
		try
		{
			// given
			// when
			Thumbnails.of("non-existent-file")
				.size(200, 200)
				.alphaInterpolation(null);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Alpha interpolation is null.", e.getMessage());
			throw e;
		}
	}