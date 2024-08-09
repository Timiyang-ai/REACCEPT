@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public Patient getPatientByUuid(String uuid) throws APIException;