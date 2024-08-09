public Set<Encounter> getEncounters(Patient who, Date fromDate, Date toDate) {
    	return context.getDAOContext().getEncounterDAO().getEncounters(who, fromDate, toDate);
    }