@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public LocationTag getLocationTagByName(String tag) throws APIException;