@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public LocationTag getLocationTag(Integer locationTagId) throws APIException;