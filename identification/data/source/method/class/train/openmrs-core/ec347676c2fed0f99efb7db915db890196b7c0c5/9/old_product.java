@SuppressWarnings("unchecked")
	public List<Drug> getDrugs(String drugName, Concept concept, boolean includeRetired) throws DAOException {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(Drug.class, "drug");
		if (includeRetired == false)
			searchCriteria.add(Expression.eq("drug.retired", false));
		if (concept != null)
			searchCriteria.add(Expression.eq("drug.concept", concept));
		if (drugName != null)
			searchCriteria.add(Expression.eq("drug.name", drugName));
		return (List<Drug>) searchCriteria.list();
	}