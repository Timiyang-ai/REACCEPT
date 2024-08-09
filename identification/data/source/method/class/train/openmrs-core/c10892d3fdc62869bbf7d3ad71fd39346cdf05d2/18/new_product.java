@Authorized( { PrivilegeConstants.GET_PATIENTS })
	public List<Patient> getAllPatients(boolean includeVoided) throws APIException;