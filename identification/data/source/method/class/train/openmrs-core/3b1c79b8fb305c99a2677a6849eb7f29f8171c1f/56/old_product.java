@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public List<Provider> getProviders(String query, Integer start, Integer length,
	        Map<ProviderAttributeType, Object> attributes, boolean includeRetired);