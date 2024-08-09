@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public List<Provider> getAllProviders(boolean includeRetired);