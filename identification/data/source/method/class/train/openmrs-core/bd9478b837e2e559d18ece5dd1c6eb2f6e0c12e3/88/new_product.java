@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Location loc, Date fromDate, Date toDate);