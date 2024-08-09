@Deprecated
	@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public List<Obs> getObservations(Concept c, Location loc, String sort, Integer personType, boolean includeVoided);