public List<User> getAllUsers(List<Role> roles, boolean includeVoided) {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_VIEW_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_VIEW_USERS);
		
		Role auth_role = getRole(OpenmrsConstants.AUTHENTICATED_ROLE);
		
		if (roles.contains(auth_role))
			return getUserDAO().getAllUsers(getRoles(), includeVoided);
		
		return getUserDAO().getAllUsers(roles, includeVoided);
	}