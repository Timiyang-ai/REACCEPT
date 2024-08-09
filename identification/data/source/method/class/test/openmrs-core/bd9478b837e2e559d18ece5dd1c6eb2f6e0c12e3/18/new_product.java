@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENTS })
	@Transactional(readOnly = true)
	public Patient getPatient(Integer patientId) throws APIException;