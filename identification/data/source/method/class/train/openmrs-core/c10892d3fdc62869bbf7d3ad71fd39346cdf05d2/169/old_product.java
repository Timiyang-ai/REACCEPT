@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public List<Location> getLocationsByTag(LocationTag tag) throws APIException;