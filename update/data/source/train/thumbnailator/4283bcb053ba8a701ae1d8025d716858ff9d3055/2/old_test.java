@Test(expected=NullPointerException.class)
	public void resizer_Null()
	{
		try
		{
			// given
			// when
			Thumbnails.of("non-existent-file")
				.size(200, 200)
				.resizer(null);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Resizer is null.", e.getMessage());
			throw e;
		}
	}