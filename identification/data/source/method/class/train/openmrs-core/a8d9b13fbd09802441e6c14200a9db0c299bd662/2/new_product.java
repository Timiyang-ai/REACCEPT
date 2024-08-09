@SuppressWarnings("unchecked")
	public List<EncounterType> getAllEncounterTypes(Boolean includeRetired) throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		if (includeRetired == false)
			criteria.add(Expression.eq("retired", false));
		List<EncounterType> results = criteria.list();
		
		// do java sorting on the return value of "getName()",
		// because maybe both unlocalized and localized encounterTypes are in "results" list
		Collections.sort(results, new Comparator<EncounterType>() {
			@Override
			public int compare(EncounterType left, EncounterType right) {
				return left.getName().compareTo(right.getName());
			}
		});
		return results;
	}