public Set<Obs> getObservations(Patient who, Concept question) {
    	return getObsDAO().getObservations(who, question);
    }