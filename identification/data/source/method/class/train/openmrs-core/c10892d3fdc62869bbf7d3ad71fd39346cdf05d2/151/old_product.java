@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public List<Location> getLocationsByTag(LocationTag tag) throws APIException;