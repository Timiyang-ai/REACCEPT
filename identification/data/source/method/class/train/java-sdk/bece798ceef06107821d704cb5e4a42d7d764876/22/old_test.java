@Test
	@Ignore
	public void testExtract() {
		service.setDataset(RelationshipExtractionDataset.ENGLISH_NEWS);
		String response = service.extract("IBM Watson Developer Cloud");
		Assert.assertNotNull(response);
	}