@Deprecated
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncounters(Patient who, Date fromDate, Date toDate);