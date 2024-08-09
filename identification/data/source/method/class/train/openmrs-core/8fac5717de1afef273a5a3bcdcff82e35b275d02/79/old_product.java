@Transactional(readOnly=true)
	public List<Cohort> getCohortsContainingPatient(Patient patient);