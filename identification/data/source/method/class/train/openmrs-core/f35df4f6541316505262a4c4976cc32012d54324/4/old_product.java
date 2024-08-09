@Authorized( { PrivilegeConstants.VIEW_PATIENTS })
	public Integer getCountOfPatients(String query, boolean includeVoided);