@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_PERSONS })
	public List<Person> getPeople(String searchPhrase, Boolean dead) throws APIException;