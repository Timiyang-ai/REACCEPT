@SuppressWarnings("unchecked")
	public List<Person> getPeople(String name, Boolean dead) {
		name = name.replace(", ", " ");
		String[] names = name.split(" ");
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Person.class);
		criteria.createAlias("names", "name");
		for (String n : names) {
			if (n != null && n.length() > 0) {
				criteria.add(Expression.or(Expression.like("name.givenName", n, MatchMode.START), Expression.or(Expression
				        .like("name.familyName", n, MatchMode.START), Expression.or(Expression.like("name.middleName", n,
				    MatchMode.START), Expression.like("name.familyName2", n, MatchMode.START)))));
			}
		}
		
		criteria.add(Expression.eq("personVoided", false));
		if (dead != null)
			criteria.add(Expression.eq("dead", dead));
		criteria.addOrder(Order.asc("personId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.setMaxResults(getMaximumSearchResults());
		return criteria.list();
	}