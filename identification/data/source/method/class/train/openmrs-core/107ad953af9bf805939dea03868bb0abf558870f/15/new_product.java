public Set<Obs> getObservations(Encounter whichEncounter) {
		Session session = HibernateUtil.currentSession();

		Query query = session.createQuery("from Obs obs where obs.encounter = :e");
		query.setParameter("e", whichEncounter);
		Set<Obs> ret = new HashSet<Obs>(query.list());

		return ret;
	}