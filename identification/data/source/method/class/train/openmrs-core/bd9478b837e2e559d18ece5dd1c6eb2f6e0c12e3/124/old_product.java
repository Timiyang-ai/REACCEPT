public Set<Obs> getObservations(Patient who) {
		return context.getDAOContext().getObsDAO().getObservations(who);
	}