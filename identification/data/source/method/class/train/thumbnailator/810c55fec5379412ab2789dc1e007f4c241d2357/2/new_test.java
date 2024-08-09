	@Test(expected=IllegalStateException.class)
	public void build_NothingSet()
	{
		new ThumbnailParameterBuilder().build();
		
		fail();
	}