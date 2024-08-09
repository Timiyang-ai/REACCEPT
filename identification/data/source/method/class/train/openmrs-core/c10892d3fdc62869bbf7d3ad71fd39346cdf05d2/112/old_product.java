@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	@Transactional(readOnly = true)
	public List<Patient> getAllPatients() throws APIException;