@Override
	public Collection<Provider> getProvidersByPerson(Person person) {
		Criteria criteria = getSession().createCriteria(Provider.class);
		criteria.add(Restrictions.eq("person", person));
		criteria.addOrder(Order.asc("providerId"));
		@SuppressWarnings("unchecked")
		List<Provider> list = criteria.list();
		return list;
	}