@SuppressWarnings("unchecked")
	public Set<Obs> getObservations(Patient who, Concept question) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Obs obs where obs.patient = :p and obs.concept = :c");
		query.setParameter("p", who);
		query.setParameter("c", question);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		return ret;
	}