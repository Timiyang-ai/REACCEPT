@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Date fromDate, Date toDate);