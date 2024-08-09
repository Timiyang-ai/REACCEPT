@Authorized( { OpenmrsConstants.PRIV_EDIT_USERS })
	public void changeHashedPassword(User user, String hashedPassword, String salt) throws APIException;