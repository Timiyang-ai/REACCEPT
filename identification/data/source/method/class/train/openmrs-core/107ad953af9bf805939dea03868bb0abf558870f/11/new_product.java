@SuppressWarnings("unchecked")
	public Set<Obs> getObservations(Person who) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Obs obs where obs.person = :p");
		query.setParameter("p", who);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		return ret;
	}