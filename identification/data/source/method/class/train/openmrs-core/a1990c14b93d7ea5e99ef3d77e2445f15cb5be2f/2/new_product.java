@SuppressWarnings("unchecked")
	public List<Person> getPeople(String searchString, Boolean dead) {
		if (searchString == null)
			return new ArrayList<Person>();
		
		searchString = searchString.replace(", ", " ");
		String[] values = searchString.split(" ");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
		
		criteria.createAlias("names", "name", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("attributes", "attribute", CriteriaSpecification.LEFT_JOIN);
		criteria.createAlias("attribute.attributeType", "attributeType", CriteriaSpecification.LEFT_JOIN);
		
		criteria.add(Restrictions.eq("personVoided", false));
		if (dead != null)
			criteria.add(Restrictions.eq("dead", dead));
		
		Disjunction disjunction = Restrictions.disjunction();
		for (String value : values) {
			if (value != null && value.length() > 0) {
				disjunction.add(
				    Restrictions.conjunction().add(Restrictions.eq("name.voided", false)).add(
				        Restrictions.disjunction().add(Restrictions.ilike("name.givenName", value, MatchMode.START)).add(
				            Restrictions.ilike("name.middleName", value, MatchMode.START)).add(
				            Restrictions.ilike("name.familyName", value, MatchMode.START)).add(
				            Restrictions.ilike("name.familyName2", value, MatchMode.START)))).add(
				    Restrictions.conjunction().add(Restrictions.eq("attributeType.searchable", true)).add(
				        Restrictions.eq("attribute.voided", false)).add(
				        Restrictions.ilike("attribute.value", value, MatchMode.ANYWHERE)));
			}
		}
		criteria.add(disjunction);
		
		criteria.addOrder(Order.asc("personId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(getMaximumSearchResults());
		return criteria.list();
	}