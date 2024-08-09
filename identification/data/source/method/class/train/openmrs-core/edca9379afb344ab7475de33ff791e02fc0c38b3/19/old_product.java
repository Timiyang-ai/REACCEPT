@Authorized(PrivilegeConstants.VIEW_OBS)
	public Integer getObservationCount(List<Person> whom, List<Encounter> encounters, List<Concept> questions,
	        List<Concept> answers, List<PERSON_TYPE> personTypes, List<Location> locations, Integer obsGroupId,
	        Date fromDate, Date toDate, boolean includeVoidedObs) throws APIException;