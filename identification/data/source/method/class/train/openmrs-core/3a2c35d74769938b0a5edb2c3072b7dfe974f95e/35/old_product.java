@Authorized( { OpenmrsConstants.PRIV_EDIT_PATIENTS })
	public void mergePatients(Patient preferred, Patient notPreferred) throws APIException;