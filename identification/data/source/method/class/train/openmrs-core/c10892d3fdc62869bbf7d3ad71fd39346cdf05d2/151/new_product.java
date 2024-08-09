@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<Location> getLocationsByTag(LocationTag tag) throws APIException;