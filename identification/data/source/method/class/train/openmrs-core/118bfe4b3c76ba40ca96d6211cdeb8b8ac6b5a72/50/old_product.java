@Transactional(readOnly = true)
	public Person getPersonByUuid(String uuid) throws APIException;