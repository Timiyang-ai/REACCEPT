@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public List<Patient> getPatients(String query, boolean includeVoided, Integer start, Integer length) throws APIException;