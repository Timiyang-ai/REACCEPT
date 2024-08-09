@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public List<LocationTag> getAllLocationTags(boolean includeRetired) throws APIException;