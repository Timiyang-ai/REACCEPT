@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public List<LocationTag> getAllLocationTags(boolean includeRetired) throws APIException;