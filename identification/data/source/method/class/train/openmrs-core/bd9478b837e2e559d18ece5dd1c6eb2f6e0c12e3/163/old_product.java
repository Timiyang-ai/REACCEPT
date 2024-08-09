@Transactional(readOnly = true)
	public Set<Obs> getObservations(Encounter whichEncounter);