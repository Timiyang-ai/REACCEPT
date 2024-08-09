@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENTS })
	public Patient getPatientByUuid(String uuid) throws APIException;