@Authorized(PrivilegeConstants.VIEW_VISITS)
	public List<Visit> getVisitsByPatient(Patient patient) throws APIException;