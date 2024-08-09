@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public List<Location> getLocations(String nameFragment) throws APIException;