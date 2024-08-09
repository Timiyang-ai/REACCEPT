	@Test
	public void addSetMember_shouldAddTheConceptToTheCurrentListOfConceptSet() {
		Concept concept = new Concept();
		Concept setMember = new Concept(1);
		
		Assert.assertEquals(0, concept.getConceptSets().size());
		
		concept.addSetMember(setMember);
		
		Assert.assertEquals(1, concept.getConceptSets().size());
		
	}