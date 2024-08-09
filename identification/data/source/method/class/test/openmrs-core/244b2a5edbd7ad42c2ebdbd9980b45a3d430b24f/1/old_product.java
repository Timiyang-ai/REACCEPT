@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LOCATION_ATTRIBUTE_TYPES)
	LocationAttributeType getLocationAttributeType(Integer id);