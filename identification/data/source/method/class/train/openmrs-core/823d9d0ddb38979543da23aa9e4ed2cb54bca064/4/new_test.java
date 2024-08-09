	@Test
	public void saveConceptClass_shouldSaveTheGivenConceptClass() {
		int unusedConceptClassId = 123;
		ConceptClass conceptClass = new ConceptClass(unusedConceptClassId);
		conceptClass.setName("name");
		conceptClass.setDescription("description");
		conceptService.saveConceptClass(conceptClass);
		assertEquals(conceptClass, conceptService.getConceptClass(unusedConceptClassId));
	}