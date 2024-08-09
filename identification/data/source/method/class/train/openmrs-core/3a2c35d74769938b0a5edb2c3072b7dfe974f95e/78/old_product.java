public boolean hasDuplicateUsername(User user) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_USERS);
		return getUserDAO().hasDuplicateUsername(user);
	}