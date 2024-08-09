@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Date fromDate, Date toDate);