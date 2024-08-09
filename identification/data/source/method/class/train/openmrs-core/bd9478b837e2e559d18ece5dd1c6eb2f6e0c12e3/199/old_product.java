@Transactional(readOnly=true)
	public Collection<Encounter> getEncounters(Patient who,
                                               Location loc,
                                               Date fromDate,
                                               Date toDate,
                                               Collection<Form> enteredViaForms,
                                               Collection<EncounterType> encounterTypes,
                                               boolean includeVoided);