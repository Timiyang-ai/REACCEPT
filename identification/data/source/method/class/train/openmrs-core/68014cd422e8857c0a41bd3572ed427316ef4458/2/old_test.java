	@Test
	public void removeDescription_shouldRemoveDescriptionPassedFromListOfDescriptions() {
		Concept c = new Concept();
		ConceptDescription c1 = new ConceptDescription(1);
		c1.setDescription("Description 1");
		ConceptDescription c2 = new ConceptDescription(2);
		c2.setDescription("Description 2");
		c.addDescription(c1);
		c.addDescription(c2);
		Collection<ConceptDescription> descriptions = c.getDescriptions();
		Assert.assertEquals(2, descriptions.size());
		c.removeDescription(c1);
		descriptions = c.getDescriptions();
		Assert.assertTrue(descriptions.contains(c2));
		Assert.assertEquals(1, descriptions.size());
	}