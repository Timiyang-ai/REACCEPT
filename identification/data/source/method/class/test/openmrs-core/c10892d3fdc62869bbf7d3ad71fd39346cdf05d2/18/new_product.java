@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public LocationTag getLocationTagByName(String tag) throws APIException;