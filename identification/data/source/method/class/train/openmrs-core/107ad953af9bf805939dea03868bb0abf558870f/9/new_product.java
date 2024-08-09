public Set<Obs> getObservations(Patient who) {
		Session session = HibernateUtil.currentSession();
		HibernateUtil.beginTransaction();

		Query query = session.createQuery("from Obs obs where obs.patient = :p");
		query.setParameter("p", who);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		HibernateUtil.commitTransaction();
		return ret;
	}