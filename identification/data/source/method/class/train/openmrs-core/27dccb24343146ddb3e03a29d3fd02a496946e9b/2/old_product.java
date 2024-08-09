@SuppressWarnings("unchecked")
	public EncounterType getEncounterType(String name) throws DAOException {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(EncounterType.class);
		HibernateUtil.addEqCriterionForLocalizedColumn(name, "name", crit);
		List<EncounterType> types = crit.list();
		if (null == types || types.isEmpty()) {
			return null;
		}
		return types.get(0);
	}