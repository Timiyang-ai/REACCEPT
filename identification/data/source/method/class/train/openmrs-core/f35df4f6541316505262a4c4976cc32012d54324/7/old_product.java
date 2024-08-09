@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public Integer getCountOfPatients(String query);