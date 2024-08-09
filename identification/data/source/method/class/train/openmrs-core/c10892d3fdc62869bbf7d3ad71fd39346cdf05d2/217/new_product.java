@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<LocationTag> getLocationTags(String search) throws APIException;