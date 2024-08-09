@SuppressWarnings("unchecked")
	public Set<Obs> getObservations(Person who, Concept question, boolean includeVoided) {
		String s = "from Obs obs where obs.person = :p and obs.concept = :c";
		if (!includeVoided)
			s += " and obs.voided = false";
		Query query = sessionFactory.getCurrentSession().createQuery(s);
		query.setParameter("p", who);
		query.setParameter("c", question);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		return ret;
	}