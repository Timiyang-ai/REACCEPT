@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public LocationTag getLocationTag(Integer locationTagId) throws APIException;