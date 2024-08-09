public void changePassword(User u, String pw) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_USERS);
		
		getUserDAO().changePassword(u, pw);
	}