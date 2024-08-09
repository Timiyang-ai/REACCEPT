@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public Set<Obs> getObservations(Person who, boolean includeVoided);