@Deprecated
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Date fromDate, Date toDate);