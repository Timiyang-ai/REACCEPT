public List<Encounter> getEncounters(Patient patient, Location location, Date fromDate, Date toDate,
	                                     Collection<Form> enteredViaForms, Collection<EncounterType> encounterTypes,
	                                     Collection<User> providers, boolean includeVoided);