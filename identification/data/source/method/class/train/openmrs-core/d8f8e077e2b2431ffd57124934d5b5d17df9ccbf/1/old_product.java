@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_OBS)
	public List<Obs> getObservationsByPersonAndConcept(Person who, Concept question) throws APIException;