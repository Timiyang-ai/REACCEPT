@Transactional(readOnly = true)
	@Authorized( { PrivilegeConstants.VIEW_LOCATIONS })
	public List<LocationTag> getLocationTags(String search) throws APIException;