@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_ENCOUNTERS })
	public List<Encounter> getEncounters(Patient who, Location loc, Date fromDate, Date toDate,
	        Collection<Form> enteredViaForms, Collection<EncounterType> encounterTypes, Collection<Provider> providers,
	        Collection<VisitType> visitTypes, Collection<Visit> visits, boolean includeVoided);