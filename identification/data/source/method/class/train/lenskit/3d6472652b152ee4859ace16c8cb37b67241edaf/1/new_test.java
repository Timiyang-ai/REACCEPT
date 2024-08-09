@Test
	public void testSetItemSimilarity() {
		module.knn.setItemSimilarity(DummySimilarity.class);
		assertThat(module.knn.getItemSimilarity(), isAssignableTo(DummySimilarity.class));
	}