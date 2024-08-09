@Transactional(readOnly=true)
	@Authorized({"View Users"})
	User getUser(Integer userId) throws APIException;