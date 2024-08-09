@SuppressWarnings("unchecked")
	public List<Drug> getDrugs(String drugName, Concept concept, boolean includeRetired) throws DAOException {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(Drug.class, "drug");
		if (includeRetired == false)
			searchCriteria.add(Restrictions.eq("drug.retired", false));
		if (concept != null)
			searchCriteria.add(Restrictions.eq("drug.concept", concept));
		if (drugName != null)
			searchCriteria.add(Restrictions.eq("drug.name", drugName));
		return (List<Drug>) searchCriteria.list();
	}