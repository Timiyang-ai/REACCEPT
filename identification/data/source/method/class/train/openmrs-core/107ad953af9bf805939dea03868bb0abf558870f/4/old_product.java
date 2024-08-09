@SuppressWarnings("unchecked")
	public List<Obs> getObservations(List<Person> whom, List<Encounter> encounters, List<Concept> questions,
	                                 List<Concept> answers, List<PERSON_TYPE> personTypes, List<Location> locations,
	                                 List<String> sortList, Integer mostRecentN, Integer obsGroupId, Date fromDate,
	                                 Date toDate, boolean includeVoidedObs) throws DAOException {
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Obs.class, "obs");
		
		if (whom.size() > 0)
			criteria.add(Restrictions.in("person", whom));
		
		if (encounters.size() > 0)
			criteria.add(Restrictions.in("encounter", encounters));
		
		if (questions.size() > 0)
			criteria.add(Restrictions.in("concept", questions));
		
		if (answers.size() > 0)
			criteria.add(Restrictions.in("valueCoded", answers));
		
		getCriteriaPersonModifier(criteria, personTypes);
		
		if (locations.size() > 0)
			criteria.add(Restrictions.in("location", locations));
		
		// TODO add an option for each sort item to be asc/desc
		if (sortList.size() > 0) {
			for (String sort : sortList) {
				if (sort != null && !"".equals(sort))
					criteria.addOrder(Order.desc(sort));
			}
		}
		
		if (mostRecentN > 0)
			criteria.setMaxResults(mostRecentN);
		
		if (obsGroupId != null) {
			criteria.createAlias("obsGroup", "og");
			criteria.add(Restrictions.eq("og.obsId", obsGroupId));
		}
		
		if (fromDate != null)
			criteria.add(Restrictions.gt("obsDatetime", fromDate));
		
		if (toDate != null)
			criteria.add(Restrictions.lt("obsDatetime", toDate));
		
		if (includeVoidedObs == false)
			criteria.add(Restrictions.eq("voided", false));
		
		return criteria.list();
	}