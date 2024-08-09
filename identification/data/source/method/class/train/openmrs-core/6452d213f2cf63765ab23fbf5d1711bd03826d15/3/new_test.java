	@Test
	public void saveConceptNameTag_shouldSaveAConceptNameTagIfATagDoesNotExist() {
		ConceptNameTag nameTag = new ConceptNameTag();
		nameTag.setTag("a new tag");
		
		ConceptNameTag savedNameTag = conceptService.saveConceptNameTag(nameTag);
		
		assertNotNull(nameTag.getId());
		assertEquals(savedNameTag.getId(), nameTag.getId());
	}