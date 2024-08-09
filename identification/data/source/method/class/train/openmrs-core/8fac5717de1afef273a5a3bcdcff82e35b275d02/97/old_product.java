@Transactional(readOnly=true)
	@Authorized({"View People"})
	public Person getPerson(Integer personId) throws APIException;