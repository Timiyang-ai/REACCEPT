@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTERS })
	public Collection<Encounter> getEncounters(Location loc, Date fromDate, Date toDate);