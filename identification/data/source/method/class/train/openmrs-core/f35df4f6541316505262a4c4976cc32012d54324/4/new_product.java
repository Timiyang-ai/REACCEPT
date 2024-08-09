@Authorized( { PrivilegeConstants.GET_PATIENTS })
	public Integer getCountOfPatients(String query, boolean includeVoided);