@Authorized(PrivilegeConstants.GET_VISITS)
	public List<Visit> getVisitsByPatient(Patient patient, boolean includeInactive, boolean includeVoided)
	        throws APIException;