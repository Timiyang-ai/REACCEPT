	@Test
	public void removeConceptMapping_shouldRemoveConceptMapPassedFromListOfMappings() {
		Concept c = new Concept();
		ConceptMap c1 = new ConceptMap(1);
		c1.setConceptMapType(new ConceptMapType(1));
		ConceptMap c2 = new ConceptMap(2);
		c2.setConceptMapType(new ConceptMapType(2));
		c.addConceptMapping(c1);
		c.addConceptMapping(c2);
		Collection<ConceptMap> mappings = c.getConceptMappings();
		Assert.assertEquals(2, mappings.size());
		c.removeConceptMapping(c1);
		mappings = c.getConceptMappings();
		Assert.assertTrue(mappings.contains(c2));
		Assert.assertEquals(1, mappings.size());
	}