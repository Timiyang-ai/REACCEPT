@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PATIENTS })
	public List<Patient> getPatients(String query) throws APIException;