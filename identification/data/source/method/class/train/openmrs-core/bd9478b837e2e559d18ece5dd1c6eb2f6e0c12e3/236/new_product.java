@Transactional(readOnly=true)
	public Set<Encounter> getEncounters(Patient who, boolean includeVoided);