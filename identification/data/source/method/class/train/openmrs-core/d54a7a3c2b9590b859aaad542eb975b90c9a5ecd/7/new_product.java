@Authorized(PrivilegeConstants.GET_VISITS)
	public List<Visit> getVisitsByPatient(Patient patient) throws APIException;