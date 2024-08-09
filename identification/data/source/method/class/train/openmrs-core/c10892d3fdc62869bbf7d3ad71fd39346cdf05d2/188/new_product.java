@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public List<Patient> getAllPatients(boolean includeVoided) throws APIException;