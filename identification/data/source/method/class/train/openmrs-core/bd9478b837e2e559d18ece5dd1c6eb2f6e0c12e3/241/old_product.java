public void changePassword(String pw, String pw2) throws APIException {
		// TODO or check if its the current user
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_MANAGE_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_MANAGE_USERS);
		getUserDAO().changePassword(pw, pw2);
	}