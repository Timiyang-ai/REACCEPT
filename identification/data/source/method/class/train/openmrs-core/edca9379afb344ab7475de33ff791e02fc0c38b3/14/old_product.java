@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public Integer getObservationCount(List<ConceptName> conceptNames, boolean includeVoided);