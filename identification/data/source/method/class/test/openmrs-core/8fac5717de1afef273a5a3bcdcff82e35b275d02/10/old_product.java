@Authorized( { PrivilegeConstants.VIEW_PERSONS })
	public Set<Person> getSimilarPeople(String nameSearch, Integer birthyear, String gender) throws APIException;