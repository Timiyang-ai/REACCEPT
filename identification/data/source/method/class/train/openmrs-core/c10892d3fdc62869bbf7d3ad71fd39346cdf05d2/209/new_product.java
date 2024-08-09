@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<Location> getLocationsHavingAllTags(List<LocationTag> tags) throws APIException;