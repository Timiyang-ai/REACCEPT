@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public Provider getUnknownProvider();