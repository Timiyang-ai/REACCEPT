@SuppressWarnings("unchecked")
	public List<EncounterType> getAllEncounterTypes(Boolean includeRetired) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		if (includeRetired == false)
			criteria.add(Expression.eq("retired", false));
		List<EncounterType> results = criteria.list();
		Collections.sort(results, new MetadataNameComparator(true));
		return results;
	}