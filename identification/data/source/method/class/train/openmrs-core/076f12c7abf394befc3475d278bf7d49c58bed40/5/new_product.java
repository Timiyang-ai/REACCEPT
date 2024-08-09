@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public List<Patient> getPatients(String query) throws APIException;