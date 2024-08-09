@Authorized(PrivilegeConstants.GET_FORMS)
	public List<Form> getForms(String fuzzyName, boolean onlyLatestVersion);