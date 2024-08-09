@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public Location getDefaultLocation() throws APIException;