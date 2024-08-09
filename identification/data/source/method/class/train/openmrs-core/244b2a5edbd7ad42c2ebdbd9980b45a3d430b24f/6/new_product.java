@Transactional(readOnly = true)
	@Authorized(PrivilegeConstants.GET_LOCATIONS)
	LocationAttribute getLocationAttributeByUuid(String uuid);