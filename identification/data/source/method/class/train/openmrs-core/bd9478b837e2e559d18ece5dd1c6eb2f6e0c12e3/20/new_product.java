@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public Set<Obs> getObservations(Encounter whichEncounter);