@Authorized( { PrivilegeConstants.ADD_PATIENTS, PrivilegeConstants.EDIT_PATIENTS })
	public Patient savePatient(Patient patient) throws APIException;