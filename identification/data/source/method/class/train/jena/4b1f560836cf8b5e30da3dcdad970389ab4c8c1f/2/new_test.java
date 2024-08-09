	@Test
	public void setReducedTest() throws Exception {
		Query query = builder.query;
		assertFalse(query.isDistinct());
		assertFalse(query.isReduced());

		query = builder.setReduced(true).query;
		assertFalse(query.isDistinct());
		assertTrue(query.isReduced());

		query = builder.setDistinct(false).query;
		assertFalse(query.isDistinct());
		assertTrue(query.isReduced());

		query = builder.setDistinct(true).query;
		assertTrue(query.isDistinct());
		assertFalse(query.isReduced());

		query = builder.setReduced(true).query;
		assertFalse(query.isDistinct());
		assertTrue(query.isReduced());

		query = builder.setReduced(false).query;
		assertFalse(query.isDistinct());
		assertFalse(query.isReduced());
	}