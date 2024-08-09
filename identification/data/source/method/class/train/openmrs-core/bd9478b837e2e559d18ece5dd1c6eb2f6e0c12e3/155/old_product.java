public Set<Encounter> getEncounters(Patient who, Location where) {
    	return getEncounterDAO().getEncounters(who, where);
    }