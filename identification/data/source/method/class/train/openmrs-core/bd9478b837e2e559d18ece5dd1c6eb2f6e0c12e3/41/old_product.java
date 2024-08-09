public List<User> getUsersByRole(Role role) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_MANAGE_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_MANAGE_USERS);
		return getUserDAO().getUsersByRole(role);
	}