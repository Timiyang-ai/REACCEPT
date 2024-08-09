@Override
	public Collection<Provider> getProvidersByPerson(Person person, boolean includeRetired) {
		Criteria criteria = getSession().createCriteria(Provider.class);
		if (!includeRetired) {
			criteria.add(Expression.eq("retired", true));
		} else {
			//push retired Provider to the end of the returned list
			criteria.addOrder(Order.asc("retired"));
		}
		criteria.add(Restrictions.eq("person", person));
		
		criteria.addOrder(Order.asc("providerId"));
		
		return criteria.list();
	}