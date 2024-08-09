public void changePassword(String pw, String pw2) throws APIException {
		if (!context.hasPrivilege(OpenmrsConstants.PRIV_EDIT_USERS))
			throw new APIAuthenticationException("Privilege required: " + OpenmrsConstants.PRIV_EDIT_USERS);
		getUserDAO().changePassword(pw, pw2);
	}