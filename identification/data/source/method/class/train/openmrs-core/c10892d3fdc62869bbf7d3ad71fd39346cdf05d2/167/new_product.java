@Authorized( { PrivilegeConstants.GET_PERSONS })
	public List<Person> getPeople(String searchPhrase, Boolean dead) throws APIException;