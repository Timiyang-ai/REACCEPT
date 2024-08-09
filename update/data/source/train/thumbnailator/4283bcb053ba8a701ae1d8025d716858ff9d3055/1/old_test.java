@Test(expected=NullPointerException.class)
	public void dithering_Null()
	{
		try
		{
			// given
			// when
			Thumbnails.of("non-existent-file")
				.size(200, 200)
				.dithering(null);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Dithering is null.", e.getMessage());
			throw e;
		}
	}