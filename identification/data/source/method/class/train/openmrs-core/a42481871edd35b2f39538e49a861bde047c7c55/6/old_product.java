public List<User> getAllUsers(List<String> roles, boolean includeVoided) {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_USERS);
		return getUserDAO().getAllUsers(roles, includeVoided);
	}