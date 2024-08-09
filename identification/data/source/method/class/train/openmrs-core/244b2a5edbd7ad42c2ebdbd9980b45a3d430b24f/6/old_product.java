@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.VIEW_LOCATIONS)
	LocationAttribute getLocationAttributeByUuid(String uuid);