@Transactional(readOnly=true)
	@Authorized({"View People"})
	public Person getPerson(Patient pat) throws APIException;