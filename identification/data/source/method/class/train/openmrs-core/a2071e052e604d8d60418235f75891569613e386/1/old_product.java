private SortedSet<PatientState> getSortedStates() {
		return Collections.unmodifiableSortedSet(new TreeSet<PatientState>(getStates()));
	}