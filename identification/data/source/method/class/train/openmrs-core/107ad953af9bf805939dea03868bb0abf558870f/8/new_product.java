public Set<Obs> getObservations(Patient who, Concept question) {
		Session session = HibernateUtil.currentSession();
		HibernateUtil.beginTransaction();

		Query query = session.createQuery("from Obs obs where obs.patient = :p and concept = :c");
		query.setParameter("p", who);
		query.setParameter("c", question);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		HibernateUtil.commitTransaction();
		return ret;
	}