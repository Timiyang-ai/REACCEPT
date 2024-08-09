@Transactional(readOnly = true)
	public User getUserByUuid(String uuid) throws APIException;