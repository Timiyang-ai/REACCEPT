@Transactional(readOnly = true)
	public PersonAddress getPersonAddressByUuid(String uuid) throws APIException;