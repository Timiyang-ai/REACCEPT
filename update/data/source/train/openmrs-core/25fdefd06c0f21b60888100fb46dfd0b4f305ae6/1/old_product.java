@Authorized( { OpenmrsConstants.PRIV_EDIT_USER_PASSWORDS })
	public void changeHashedPassword(User user, String hashedPassword, String salt) throws APIException;