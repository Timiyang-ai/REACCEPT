@SuppressWarnings("unchecked")
	public List<EncounterType> findEncounterTypes(String name) throws DAOException {
		List<EncounterType> results = null;
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		HibernateUtil.addLikeCriterionForLocalizedColumn(name, "name", crit, false, MatchMode.START);
		results = crit.list();
		Collections.sort(results, new MetadataComparator(Context.getLocale()));
		return results;
	}