@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.GET_LOCATION_ATTRIBUTE_TYPES)
	LocationAttributeType getLocationAttributeType(Integer id);