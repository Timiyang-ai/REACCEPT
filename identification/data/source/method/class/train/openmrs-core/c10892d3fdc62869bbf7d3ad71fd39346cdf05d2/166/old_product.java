@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public List<Location> getLocations(String nameFragment) throws APIException;