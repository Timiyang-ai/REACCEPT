public EncounterType getEncounterType(String name) throws DAOException {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		crit.add(Expression.eq("retired", false));
		crit.add(Expression.sql("name = ?", LocalizedStringUtil.escapeDelimiter(name), Hibernate.STRING));
		EncounterType encounterType = (EncounterType) crit.uniqueResult();
		
		if (encounterType == null) //search in those localized encounterTypes
			encounterType = HibernateUtil.getUniqueMetadataByLocalizedColumn(name, "name", false, EncounterType.class,
			    sessionFactory);
		
		return encounterType;
	}