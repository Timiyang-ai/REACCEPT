@Transactional(readOnly = true)
	@Authorized(OpenmrsConstants.PRIV_VIEW_FORMS)
	public List<Form> getForms(String fuzzyName, boolean onlyLatestVersion);