@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public LocationTag getLocationTagByUuid(String uuid) throws APIException;