public List<Obs> getObservations(Concept c, Location loc, String sort) {
		return getObsDAO().getObservations(c, loc, sort);
	}