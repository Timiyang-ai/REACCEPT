@Authorized( { PrivilegeConstants.DELETE_PATIENTS })
	public Patient voidPatient(Patient patient, String reason) throws APIException;