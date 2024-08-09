public Set<Encounter> getEncounters(Patient who) {
		return getEncounterDAO().getEncounters(who);
	}