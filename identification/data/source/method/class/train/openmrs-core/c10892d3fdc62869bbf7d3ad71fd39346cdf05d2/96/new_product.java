@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public Location getDefaultLocation() throws APIException;