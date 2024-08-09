public List<Drug> getDrugs(Concept concept) {

		Session session = HibernateUtil.currentSession();
		
		Criteria crit = session.createCriteria(Drug.class)
			.add(Expression.eq("concept", concept));
		
		return crit.list();
	}