@Authorized(PrivilegeConstants.GET_OBS)
	public List<Obs> getObservationsByPersonAndConcept(Person who, Concept question) throws APIException;