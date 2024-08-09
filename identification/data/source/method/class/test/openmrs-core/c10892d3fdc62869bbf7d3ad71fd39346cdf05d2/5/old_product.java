@Authorized( { OpenmrsConstants.PRIV_ADD_PATIENTS, OpenmrsConstants.PRIV_EDIT_PATIENTS })
	public Patient savePatient(Patient patient) throws APIException;