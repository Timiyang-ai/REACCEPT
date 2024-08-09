@Authorized({OpenmrsConstants.PRIV_ADD_USERS,OpenmrsConstants.PRIV_EDIT_USERS})
	public User saveUser(User user, String password) throws APIException;