@Test
	public void testSetItemSimilarity() {
		module.setItemSimilarity(DummySimilarity.class);
		assertThat(module.getItemSimilarity(), isAssignableTo(DummySimilarity.class));
	}