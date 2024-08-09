@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public LocationTag getLocationTagByName(String tag) throws APIException;