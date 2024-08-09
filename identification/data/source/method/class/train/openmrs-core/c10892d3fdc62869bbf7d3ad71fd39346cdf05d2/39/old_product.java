@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public List<Location> getAllLocations(boolean includeRetired) throws APIException;