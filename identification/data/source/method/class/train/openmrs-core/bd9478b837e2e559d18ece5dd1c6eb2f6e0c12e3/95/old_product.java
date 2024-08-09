@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Date fromDate, Date toDate);