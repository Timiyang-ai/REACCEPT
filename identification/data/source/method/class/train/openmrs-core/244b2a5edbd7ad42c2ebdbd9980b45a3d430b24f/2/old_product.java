@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LOCATION_ATTRIBUTE_TYPES)
	LocationAttributeType getLocationAttributeTypeByUuid(String uuid);