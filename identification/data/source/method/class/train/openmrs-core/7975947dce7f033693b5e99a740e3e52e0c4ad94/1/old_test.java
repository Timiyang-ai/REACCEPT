	@Test
	public void getSetMembers_shouldReturnConceptSetMembersSortedAccordingToTheSortWeight() {
		Concept c = new Concept();
		ConceptSet set0 = new ConceptSet(new Concept(0), 3.0);
		ConceptSet set1 = new ConceptSet(new Concept(1), 2.0);
		ConceptSet set2 = new ConceptSet(new Concept(2), 1.0);
		ConceptSet set3 = new ConceptSet(new Concept(3), 0.0);
		
		List<ConceptSet> sets = new ArrayList<>();
		sets.add(set0);
		sets.add(set1);
		sets.add(set2);
		sets.add(set3);
		
		c.setConceptSets(sets);
		
		List<Concept> setMembers = c.getSetMembers();
		Assert.assertEquals(4, setMembers.size());
		Assert.assertEquals(set3.getConcept(), setMembers.get(0));
		Assert.assertEquals(set2.getConcept(), setMembers.get(1));
		Assert.assertEquals(set1.getConcept(), setMembers.get(2));
		Assert.assertEquals(set0.getConcept(), setMembers.get(3));
	}