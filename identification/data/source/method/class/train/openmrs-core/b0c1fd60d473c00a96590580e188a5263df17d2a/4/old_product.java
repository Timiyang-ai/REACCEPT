@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public Location getLocationByUuid(String uuid) throws APIException;