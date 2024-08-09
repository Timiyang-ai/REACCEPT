@Transactional(readOnly = true)
	public Map<Integer, List<Encounter>> getAllEncounters(Cohort patients);