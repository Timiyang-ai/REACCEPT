public Set<Encounter> getEncounters(Patient who, Date fromDate, Date toDate) {
    	return getEncounterDAO().getEncounters(who, fromDate, toDate);
    }