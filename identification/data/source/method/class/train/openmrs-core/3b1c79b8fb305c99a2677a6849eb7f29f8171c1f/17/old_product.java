@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_PROVIDERS })
	public Collection<Provider> getProvidersByPerson(Person person, boolean includeRetired);