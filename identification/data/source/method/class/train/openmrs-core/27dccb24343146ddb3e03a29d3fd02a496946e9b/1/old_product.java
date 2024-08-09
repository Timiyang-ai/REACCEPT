public EncounterType getEncounterType(String name) throws DAOException {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		crit.add(Expression.eq("retired", false));
		crit.add(Expression.eq("name", name));
		EncounterType encounterType = (EncounterType) crit.uniqueResult();
		
		return encounterType;
	}