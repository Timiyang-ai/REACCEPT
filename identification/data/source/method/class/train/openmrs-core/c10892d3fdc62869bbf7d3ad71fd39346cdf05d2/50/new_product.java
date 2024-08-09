@Authorized( { PrivilegeConstants.GET_PATIENTS })
	public Patient getPatientByExample(Patient patientToMatch) throws APIException;