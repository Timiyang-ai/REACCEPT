@Authorized(PrivilegeConstants.VIEW_VISITS)
	public List<Visit> getActiveVisitsByPatient(Patient patient) throws APIException;