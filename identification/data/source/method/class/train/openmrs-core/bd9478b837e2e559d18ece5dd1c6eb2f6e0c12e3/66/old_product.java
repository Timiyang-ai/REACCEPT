@Authorized( { OpenmrsConstants.PRIV_DELETE_PATIENTS })
	public Patient voidPatient(Patient patient, String reason) throws APIException;