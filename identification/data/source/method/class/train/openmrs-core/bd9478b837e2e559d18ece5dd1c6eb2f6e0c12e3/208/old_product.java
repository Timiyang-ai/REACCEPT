@Deprecated
	@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public List<Obs> getObservations(Concept c, Location loc, String sort, Integer personType, boolean includeVoided);