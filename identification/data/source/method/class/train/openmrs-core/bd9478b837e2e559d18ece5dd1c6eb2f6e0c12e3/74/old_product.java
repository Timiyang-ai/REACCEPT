public Set<Encounter> getEncounters(Patient who, Location where) {
    	return context.getDAOContext().getEncounterDAO().getEncounters(who, where);
    }