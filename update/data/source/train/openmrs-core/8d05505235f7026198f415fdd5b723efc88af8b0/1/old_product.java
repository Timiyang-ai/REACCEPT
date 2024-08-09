@SuppressWarnings("unchecked")
	public List<EncounterType> findEncounterTypes(String name) throws DAOException {
		return sessionFactory.getCurrentSession().createCriteria(EncounterType.class)
		// 'ilike' case insensitive search
		.add(Expression.ilike("name", name, MatchMode.START)).addOrder(Order.asc("name")).addOrder(
			Order.asc("retired")).list();
	}