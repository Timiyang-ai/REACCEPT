@SuppressWarnings("unchecked")
	public List<Person> getPeople(String searchString, Boolean dead, Boolean voided) {
		if (searchString == null) {
			return new ArrayList<Person>();
		}
		
		PersonSearchCriteria personSearchCriteria = new PersonSearchCriteria();
		
		searchString = searchString.replace(", ", " ");
		String[] values = searchString.split(" ");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
		
		personSearchCriteria.addAliasForName(criteria);
		personSearchCriteria.addAliasForAttribute(criteria);
		if (voided == null || voided == false)
			criteria.add(Restrictions.eq("personVoided", false));
		if (dead != null) {
			criteria.add(Restrictions.eq("dead", dead));
		}
		
		Disjunction disjunction = Restrictions.disjunction();
		for (String value : values) {
			if (value != null && value.length() > 0) {
				disjunction.add(personSearchCriteria.prepareCriterionForName(value, voided)).add(
				    personSearchCriteria.prepareCriterionForAttribute(value, voided));
			}
		}
		criteria.add(disjunction);
		
		criteria.addOrder(Order.asc("personId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(getMaximumSearchResults());
		
		// TODO - remove
		log.debug(criteria.toString());
		
		return criteria.list();
	}