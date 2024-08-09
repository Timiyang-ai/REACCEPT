@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<Location> getLocations(String nameFragment, boolean includeRetired, Integer start, Integer length)
	        throws APIException;