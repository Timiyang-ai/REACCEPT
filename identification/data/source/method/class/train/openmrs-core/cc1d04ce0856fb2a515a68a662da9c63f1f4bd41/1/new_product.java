@Authorized( { PrivilegeConstants.EDIT_PATIENTS })
	public void mergePatients(Patient preferred, Patient notPreferred) throws APIException, SerializationException;