@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<Location> getLocationsHavingAnyTag(List<LocationTag> tags) throws APIException;