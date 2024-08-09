@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<Location> getAllLocations(boolean includeRetired) throws APIException;