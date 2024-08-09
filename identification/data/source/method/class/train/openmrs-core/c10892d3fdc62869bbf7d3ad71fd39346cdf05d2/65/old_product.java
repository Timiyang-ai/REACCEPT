@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public LocationTag getLocationTag(Integer locationTagId) throws APIException;