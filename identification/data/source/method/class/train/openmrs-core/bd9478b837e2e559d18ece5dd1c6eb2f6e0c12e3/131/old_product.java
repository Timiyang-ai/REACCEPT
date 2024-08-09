public List<Obs> getObservations(Concept question, String sort) {
    	return getObsDAO().getObservations(question, sort);
    }