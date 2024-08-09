@Test
	@Verifies(value = "should replace the old short name with a current one", method = "addName(ConceptName)")
	public void addName_shouldReplaceTheOldShortNameWithACurrentOne() throws Exception {
		Concept concept = new Concept();
		ConceptName oldShortName = new ConceptName("some name", Context.getLocale());
		oldShortName.setConceptNameType(ConceptNameType.SHORT);
		concept.addName(oldShortName);
		ConceptName newShortName = new ConceptName("new name", Context.getLocale());
		newShortName.setConceptNameType(ConceptNameType.SHORT);
		concept.addName(newShortName);
		Assert.assertEquals(false, oldShortName.isShort());
		Assert.assertEquals("new name", concept.getShortNameInLocale(Context.getLocale()).getName());
	}