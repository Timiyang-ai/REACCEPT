public List<Concept> getSetMembers() {
		List<Concept> conceptMembers = new ArrayList<>();
		
		Collection<ConceptSet> sortedConceptSet = getSortedConceptSets();
		
		for (ConceptSet conceptSet : sortedConceptSet) {
			conceptMembers.add(conceptSet.getConcept());
		}
		return Collections.unmodifiableList(conceptMembers);
	}