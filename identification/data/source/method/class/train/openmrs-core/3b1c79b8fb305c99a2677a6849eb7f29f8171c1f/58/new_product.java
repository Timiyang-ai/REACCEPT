@Authorized( { PrivilegeConstants.GET_PROVIDERS })
	public List<Provider> getProviders(String query, Integer start, Integer length,
	        Map<ProviderAttributeType, Object> attributes, boolean includeRetired);