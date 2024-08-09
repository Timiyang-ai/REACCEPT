public User getUser(Integer userId) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_USERS);
		return getUserDAO().getUser(userId);
	}