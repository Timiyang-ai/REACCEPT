private List<PatientState> getSortedStates() {
		List<PatientState> sortedStates = new ArrayList<>(getStates());
		Collections.sort(sortedStates);
		return sortedStates;
	}