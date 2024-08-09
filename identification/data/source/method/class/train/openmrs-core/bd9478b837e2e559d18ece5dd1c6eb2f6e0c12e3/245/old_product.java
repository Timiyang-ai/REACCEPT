public Set<Encounter> getEncounters(Patient who) {
		return context.getDAOContext().getEncounterDAO().getEncounters(who);
	}