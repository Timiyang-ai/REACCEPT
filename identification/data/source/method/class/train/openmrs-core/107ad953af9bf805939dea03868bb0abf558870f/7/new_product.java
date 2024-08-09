@SuppressWarnings("unchecked")
	public List<Obs> getObservations(List<Person> whom, List<Encounter> encounters, List<Concept> questions,
	                                 List<Concept> answers, List<PERSON_TYPE> personTypes, List<Location> locations,
	                                 List<String> sortList, Integer mostRecentN, Integer obsGroupId, Date fromDate,
	                                 Date toDate, boolean includeVoidedObs) throws DAOException {
		
		Criteria criteria = createGetObservationsCriteria(whom, encounters, questions, answers, personTypes, locations,
		    sortList, mostRecentN, obsGroupId, fromDate, toDate, null, includeVoidedObs);
		
		return criteria.list();
	}