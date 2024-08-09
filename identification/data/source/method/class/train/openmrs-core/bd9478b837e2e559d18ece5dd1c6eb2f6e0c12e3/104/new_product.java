@Transactional(readOnly=true)
	@Authorized({"View Users"})
	public User getUser(Integer userId) throws APIException;