@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENTS })
	public Patient getPatientByExample(Patient patientToMatch) throws APIException;