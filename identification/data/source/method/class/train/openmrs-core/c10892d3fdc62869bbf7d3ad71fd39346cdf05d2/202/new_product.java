@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<Location> getLocations(String nameFragment) throws APIException;