@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public Integer getObservationCount(List<ConceptName> conceptNames, boolean includeVoided);