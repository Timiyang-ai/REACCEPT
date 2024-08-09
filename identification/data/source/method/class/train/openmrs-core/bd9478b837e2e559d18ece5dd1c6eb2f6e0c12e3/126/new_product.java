@Deprecated
	@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_ENCOUNTERS })
	public List<Encounter> getEncounters(Patient who, Location loc, Date fromDate, Date toDate,
		Collection<Form> enteredViaForms, Collection<EncounterType> encounterTypes,
		boolean includeVoided);