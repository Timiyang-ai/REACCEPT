@Transactional(readOnly = true)
	public List<ProviderAttributeType> getAllProviderAttributeTypes(boolean includeRetired);