@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public List<Location> getLocationsHavingAllTags(List<LocationTag> tags) throws APIException;