@Authorized(PrivilegeConstants.GET_VISITS)
	public List<Visit> getActiveVisitsByPatient(Patient patient) throws APIException;