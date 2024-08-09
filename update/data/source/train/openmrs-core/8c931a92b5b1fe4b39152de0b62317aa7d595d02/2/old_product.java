@SuppressWarnings("unchecked")
	@Override
	public List<Visit> getVisits(Collection<VisitType> visitTypes, Collection<Patient> patients,
	        Collection<Location> locations, Collection<Concept> indications, Date minStartDatetime, Date maxStartDatetime,
	        Date minEndDatetime, Date maxEndDatetime, boolean includeInactive, boolean includeVoided) throws DAOException {
		
		Criteria criteria = getCurrentSession().createCriteria(Visit.class);
		
		if (CollectionUtils.isNotEmpty(visitTypes))
			criteria.add(Restrictions.in("visitType", visitTypes));
		if (CollectionUtils.isNotEmpty(patients))
			criteria.add(Restrictions.in("patient", patients));
		if (CollectionUtils.isNotEmpty(locations))
			criteria.add(Restrictions.in("location", locations));
		if (CollectionUtils.isNotEmpty(indications))
			criteria.add(Restrictions.in("indication", indications));
		
		if (minStartDatetime != null)
			criteria.add(Restrictions.ge("startDatetime", minStartDatetime));
		if (maxStartDatetime != null)
			criteria.add(Restrictions.le("startDatetime", maxStartDatetime));
		
		//active visits have null end date, so it doesn't make sense to search against it if include inactive it set to false
		if (!includeInactive)
			criteria.add(Restrictions.isNull("stopDatetime"));
		else {
			if (minEndDatetime != null)
				criteria.add(Restrictions.ge("stopDatetime", minEndDatetime));
			if (maxEndDatetime != null)
				criteria.add(Restrictions.le("stopDatetime", maxEndDatetime));
		}
		
		if (!includeVoided)
			criteria.add(Restrictions.eq("voided", false));
		
		return criteria.list();
	}