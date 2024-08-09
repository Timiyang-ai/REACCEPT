@SuppressWarnings("unchecked")
	public List<Obs> getObservations(Concept question, String sort) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Obs obs where obs.concept = :c and obs.voided = false order by "
						+ sort).setParameter("c", question);

		return query.list();
	}