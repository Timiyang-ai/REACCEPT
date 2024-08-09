@SuppressWarnings("unchecked")
	public Set<Obs> getObservations(Person who, boolean includeVoided) {
		String s = "from Obs obs where obs.person = :p";
		if (!includeVoided)
			s += " and obs.voided = false";
		Query query = sessionFactory.getCurrentSession().createQuery(s);
		query.setParameter("p", who);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		return ret;
	}