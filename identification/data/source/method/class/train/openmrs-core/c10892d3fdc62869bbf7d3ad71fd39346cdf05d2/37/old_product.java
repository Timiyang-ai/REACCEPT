@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.GET_LOCATIONS })
	public List<LocationTag> getAllLocationTags() throws APIException;