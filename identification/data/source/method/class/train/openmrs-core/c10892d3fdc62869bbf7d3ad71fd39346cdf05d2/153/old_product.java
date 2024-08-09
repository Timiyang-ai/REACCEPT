@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public LocationTag getLocationTagByUuid(String uuid) throws APIException;