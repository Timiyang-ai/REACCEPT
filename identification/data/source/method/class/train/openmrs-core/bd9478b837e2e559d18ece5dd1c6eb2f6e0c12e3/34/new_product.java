@Deprecated
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public List<Obs> getObservations(Concept question, String sort, Integer personType, boolean includeVoided);