@SuppressWarnings("unchecked")
	public Set<Obs> getObservations(Patient who) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Obs obs where obs.patient = :p");
		query.setParameter("p", who);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		return ret;
	}