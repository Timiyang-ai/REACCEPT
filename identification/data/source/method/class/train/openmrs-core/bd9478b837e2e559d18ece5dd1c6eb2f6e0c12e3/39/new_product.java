public List<Obs> getObservations(Concept c, Location loc, String sort, Integer personType) {
		return getObsDAO().getObservations(c, loc, sort, personType);
	}