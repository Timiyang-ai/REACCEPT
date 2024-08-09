@Test(expected=NullPointerException.class)
	public void antialiasing_Null()
	{
		try
		{
			// given
			// when
			Thumbnails.of("non-existent-file")
				.size(200, 200)
				.antialiasing(null);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Antialiasing is null.", e.getMessage());
			throw e;
		}
	}