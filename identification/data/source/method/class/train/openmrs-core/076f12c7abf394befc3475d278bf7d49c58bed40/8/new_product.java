@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public List<Patient> getPatients(String query, Integer start, Integer length) throws APIException;