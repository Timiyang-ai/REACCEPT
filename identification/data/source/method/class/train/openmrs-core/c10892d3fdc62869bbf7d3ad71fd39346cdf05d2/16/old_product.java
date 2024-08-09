@Transactional(readOnly = true)
	@Authorized( { OpenmrsConstants.PRIV_VIEW_LOCATIONS })
	public List<Location> getLocationsHavingAnyTag(List<LocationTag> tags) throws APIException;