	@Test
	public void conceptIterator_shouldIterateOverAllConcepts() {
		Iterator<Concept> iterator = Context.getConceptService().conceptIterator();
		
		Assert.assertTrue(iterator.hasNext());
		Assert.assertEquals(3, iterator.next().getConceptId().intValue());
	}