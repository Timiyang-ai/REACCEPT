public List<User> getUsersByRole(Role role) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_USERS);
		
		List<Role> roles = new Vector<Role>();
		roles.add(role);
		
		return getAllUsers(roles, false);
	}