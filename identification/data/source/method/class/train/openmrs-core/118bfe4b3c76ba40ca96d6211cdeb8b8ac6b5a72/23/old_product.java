@Transactional(readOnly = true)
	public PersonAttribute getPersonAttributeByUuid(String uuid) throws APIException;