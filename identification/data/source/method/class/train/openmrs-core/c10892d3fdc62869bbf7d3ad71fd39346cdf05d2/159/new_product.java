@Authorized( { PrivilegeConstants.PURGE_USERS })
	public void purgeUser(User user, boolean cascade) throws APIException;