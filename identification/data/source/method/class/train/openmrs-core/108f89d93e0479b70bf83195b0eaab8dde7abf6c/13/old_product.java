public void createUser(User user, String password) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_ADD_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_ADD_USERS);
		checkPrivileges(user);
		getUserDAO().createUser(user, password);
	}