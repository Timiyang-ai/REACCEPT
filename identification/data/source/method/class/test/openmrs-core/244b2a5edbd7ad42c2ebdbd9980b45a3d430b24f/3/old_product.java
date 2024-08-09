@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LOCATION_ATTRIBUTE_TYPES)
	List<LocationAttributeType> getAllLocationAttributeTypes();