@SuppressWarnings("unchecked")
	@Override
	public List<Drug> getDrugs(String drugName, Concept concept, boolean searchOnPhrase, boolean searchDrugConceptNames,
	        boolean includeRetired, Integer start, Integer length) throws DAOException {
		Criteria searchCriteria = sessionFactory.getCurrentSession().createCriteria(Drug.class, "drug");
		if (StringUtils.isBlank(drugName) && concept == null)
			return Collections.emptyList();
		
		if (!includeRetired)
			searchCriteria.add(Restrictions.eq("drug.retired", false));
		MatchMode matchMode = MatchMode.START;
		if (searchOnPhrase)
			matchMode = MatchMode.ANYWHERE;
		if (!StringUtils.isBlank(drugName)) {
			if (searchDrugConceptNames && concept != null) {
				searchCriteria.createCriteria("concept", "concept").createAlias("concept.names", "names");
				searchCriteria.add(Restrictions.or(Restrictions.ilike("drug.name", drugName, matchMode), Restrictions.ilike(
				    "names.name", drugName, matchMode)));
				searchCriteria.setProjection(Projections.distinct(Projections.property("drugId")));
			} else {
				searchCriteria.add(Restrictions.ilike("drug.name", drugName, matchMode));
				
			}
		}
		
		if (start != null)
			searchCriteria.setFirstResult(start);
		if (length != null && length > 0)
			searchCriteria.setMaxResults(length);
		
		return searchCriteria.list();
	}