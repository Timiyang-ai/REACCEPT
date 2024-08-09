@SuppressWarnings("unchecked")
	public EncounterType getEncounterType(String name) throws DAOException {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		crit.add(Expression.eq("retired", false));
		HibernateUtil.addEqCriterionForLocalizedColumn(name, "localizedName", crit);
		List<EncounterType> types = crit.list();
		if (null == types || types.isEmpty()) {
			return null;
		}
		return types.get(0);
	}