@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public Set<Obs> getObservations(Person who, Concept question, boolean includeVoided);