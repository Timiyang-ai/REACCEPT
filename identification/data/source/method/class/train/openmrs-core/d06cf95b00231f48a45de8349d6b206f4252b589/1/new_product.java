@Override
	public List<OrderFrequency> getOrderFrequencies(boolean includeRetired) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OrderFrequency.class);
		if (!includeRetired) {
			criteria.add(Restrictions.eq("retired", false));
		}
		return criteria.list();
	}