public User getUser(Integer userId) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_MANAGE_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_MANAGE_USERS);
		return getUserDAO().getUser(userId);
	}