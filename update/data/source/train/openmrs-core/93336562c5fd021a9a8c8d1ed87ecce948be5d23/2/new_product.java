public List<Object> findPeopleByRoles(String searchPhrase, boolean includeVoided, String roles) {
		return findBatchOfPeopleByRoles(searchPhrase, includeVoided, roles, null, null);
	}