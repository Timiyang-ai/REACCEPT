@Authorized(PrivilegeConstants.VIEW_FORMS)
	public List<Form> getForms(String fuzzyName, boolean onlyLatestVersion);