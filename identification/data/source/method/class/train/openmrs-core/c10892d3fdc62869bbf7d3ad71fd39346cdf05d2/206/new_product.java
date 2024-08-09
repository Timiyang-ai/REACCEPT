@Authorized({ PrivilegeConstants.VIEW_PATIENTS } )
	@Transactional(readOnly = true)
	public List<Patient> getAllPatients(boolean includeVoided) throws APIException;