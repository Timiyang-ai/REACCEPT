	@Test
	public void purgeConceptNameTag_shouldDeleteTheSpecifiedConceptNameTagFromTheDatabase() {
		executeDataSet("org/openmrs/api/include/ConceptServiceTest-tags.xml");
		//sanity check
		ConceptNameTag nameTag = Context.getConceptService().getConceptNameTagByName("preferred_en");
		Assert.assertNotNull(nameTag);
		Context.getConceptService().purgeConceptNameTag(nameTag);
		Assert.assertNull(Context.getConceptService().getConceptNameTagByName("preferred_en"));
	}