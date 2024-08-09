public Set<Obs> getObservations(Patient who, Concept question) {
		Session session = HibernateUtil.currentSession();
		HibernateUtil.beginTransaction();

		Query query = session.createQuery("from Obs obs where obs.patientId = :patientId and concept_id = :conceptId");
		query.setInteger("patientId", who.getPatientId());
		query.setInteger("conceptId", question.getConceptId());
		Set<Obs> ret = new HashSet<Obs>(query.list());

		HibernateUtil.commitTransaction();
		return ret;
	}