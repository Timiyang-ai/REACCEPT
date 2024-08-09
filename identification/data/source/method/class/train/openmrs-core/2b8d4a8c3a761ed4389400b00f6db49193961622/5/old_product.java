@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public Long getCountOfProviders(String query);