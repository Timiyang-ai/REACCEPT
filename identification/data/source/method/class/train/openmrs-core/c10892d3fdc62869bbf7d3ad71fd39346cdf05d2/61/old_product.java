@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public LocationTag getLocationTagByUuid(String uuid) throws APIException;