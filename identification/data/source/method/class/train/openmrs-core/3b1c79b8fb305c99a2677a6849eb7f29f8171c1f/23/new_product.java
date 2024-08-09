@Authorized( { PrivilegeConstants.GET_PROVIDERS })
	public Collection<Provider> getProvidersByPerson(Person person, boolean includeRetired);