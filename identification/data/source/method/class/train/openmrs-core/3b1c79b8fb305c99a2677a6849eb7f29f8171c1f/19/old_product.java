@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public Provider getProvider(Integer providerId);