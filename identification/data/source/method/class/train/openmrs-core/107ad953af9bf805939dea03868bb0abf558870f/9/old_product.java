public Set<Obs> getObservations(Patient who) {
		Session session = HibernateUtil.currentSession();
		HibernateUtil.beginTransaction();

		Query query = session.createQuery("from Obs obs where obs.patientId = :patientId");
		query.setInteger("patientId", who.getPatientId());
		Set<Obs> ret = new HashSet<Obs>(query.list());

		HibernateUtil.commitTransaction();
		return ret;
	}