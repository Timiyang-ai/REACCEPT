@SuppressWarnings("unchecked")
	public List<Obs> getObservations(Concept c, Location location, String sort) {
		String q = "from Obs obs where obs.location = :loc and obs.concept = :concept";
		if (sort != null && sort.length() > 0)
			q += " order by :sort";

		Query query = sessionFactory.getCurrentSession().createQuery(q);
		query.setParameter("loc", location);
		query.setParameter("concept", c);

		if (sort != null && sort.length() > 0)
			query.setParameter("sort", sort);

		return query.list();
	}