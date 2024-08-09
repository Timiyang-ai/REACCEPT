private List<PatientState> getSortedStates() {
		List<PatientState> sortedStates = new ArrayList<PatientState>(getStates());
		Collections.sort(sortedStates);
		return sortedStates;
	}