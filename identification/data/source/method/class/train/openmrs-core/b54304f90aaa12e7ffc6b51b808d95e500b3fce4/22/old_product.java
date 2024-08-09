public void addSetMember(Concept setMember, int index) {
		List<ConceptSet> sortedConceptSets = getSortedConceptSets();
		int setsSize = sortedConceptSets.size();
		
		//after sorting, we need to reset the sort weights because retired
		//sets have moved to the bottom and hence need to be reassigned
		//higher sort weights than the non retired ones
		double weight = 990.0;
		for (ConceptSet conceptSet : sortedConceptSets) {
			conceptSet.setSortWeight(weight += 10.0);
		}
		
		if (sortedConceptSets.isEmpty()) {
			weight = 1000.0;
		} else if (index == -1 || index >= setsSize) {
			// deals with list size of 1 and any large index given by dev
			weight = sortedConceptSets.get(setsSize - 1).getSortWeight() + 10.0;
		} else if (index == 0) {
			weight = sortedConceptSets.get(0).getSortWeight() - 10.0;
		} else {
			// put the weight between two
			double prevSortWeight = sortedConceptSets.get(index - 1).getSortWeight();
			double nextSortWeight = sortedConceptSets.get(index).getSortWeight();
			weight = (prevSortWeight + nextSortWeight) / 2;
		}
		
		ConceptSet conceptSet = new ConceptSet(setMember, weight);
		conceptSet.setConceptSet(this);
		conceptSets.add(conceptSet);
	}