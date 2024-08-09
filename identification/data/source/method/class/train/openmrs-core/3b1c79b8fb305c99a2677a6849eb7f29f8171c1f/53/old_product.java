@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public boolean isProviderIdentifierUnique(Provider provider) throws APIException;