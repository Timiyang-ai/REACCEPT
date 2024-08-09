@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<LocationTag> getAllLocationTags(boolean includeRetired) throws APIException;