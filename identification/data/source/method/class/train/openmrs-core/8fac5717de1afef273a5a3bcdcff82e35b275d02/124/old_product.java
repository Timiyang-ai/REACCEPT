@Transactional(readOnly=true)
	@Authorized({"View People"})
	public Person getPerson(User user) throws APIException;