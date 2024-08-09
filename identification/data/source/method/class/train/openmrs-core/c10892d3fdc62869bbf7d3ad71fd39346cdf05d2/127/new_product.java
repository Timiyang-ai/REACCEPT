@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public Patient getPatientByExample(Patient patientToMatch) throws APIException;