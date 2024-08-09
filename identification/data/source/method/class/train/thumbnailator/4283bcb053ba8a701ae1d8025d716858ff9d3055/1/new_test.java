@Test(expected=NullPointerException.class)
	public void rendering_Null()
	{
		try
		{
			// given
			// when
			Thumbnails.of("non-existent-file")
				.size(200, 200)
				.rendering(null);
		}
		catch (NullPointerException e)
		{
			// then
			assertEquals("Rendering is null.", e.getMessage());
			throw e;
		}
	}