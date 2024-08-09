@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Location loc, Date fromDate, Date toDate);