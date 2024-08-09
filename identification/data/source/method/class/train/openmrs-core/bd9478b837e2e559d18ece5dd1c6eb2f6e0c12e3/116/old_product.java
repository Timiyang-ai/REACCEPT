public Set<Obs> getObservations(Patient who) {
		return getObsDAO().getObservations(who);
	}