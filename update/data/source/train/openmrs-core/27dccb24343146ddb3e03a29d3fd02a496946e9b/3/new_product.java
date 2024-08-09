public EncounterType getEncounterType(String name) throws DAOException {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		crit.add(Restrictions.eq("retired", false));
		crit.add(Restrictions.eq("name", name));
		EncounterType encounterType = (EncounterType) crit.uniqueResult();
		
		return encounterType;
	}