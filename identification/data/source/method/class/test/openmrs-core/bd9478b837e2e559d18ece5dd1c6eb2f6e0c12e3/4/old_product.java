public Set<Obs> getObservations(Patient who, Concept question) {
    	return context.getDAOContext().getObsDAO().getObservations(who, question);
    }