@Deprecated
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public List<Obs> getObservations(List<Concept> concepts, Date fromDate, Date toDate, boolean includeVoided);