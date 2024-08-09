@Authorized( { PrivilegeConstants.GET_PATIENTS })
	public Patient getPatientByUuid(String uuid) throws APIException;