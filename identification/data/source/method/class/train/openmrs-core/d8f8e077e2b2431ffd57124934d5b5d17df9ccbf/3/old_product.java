@Deprecated
	@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_OBS)
	public List<Obs> getObservationsByPersonAndConcept(Person who, Concept question) throws APIException;