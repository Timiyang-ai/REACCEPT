public Set<Obs> getObservations(Encounter whichEncounter) {
    	return context.getDAOContext().getObsDAO().getObservations(whichEncounter);
    }