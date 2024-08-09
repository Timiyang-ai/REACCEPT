	@Test
	public void getAnimeTitles() throws Exception {
		SearchResult[] animeTitles = anidb.getAnimeTitles();
		assertTrue(animeTitles.length > 8000);
	}