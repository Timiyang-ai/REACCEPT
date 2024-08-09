@Override
	@Transactional(readOnly = true)
	public List<Location> getLocationsHavingAnyTag(List<LocationTag> tags) throws APIException {
		List<Location> locations = new ArrayList<Location>();
		
		for (Location loc : dao.getAllLocations(false)) {
			for (LocationTag t : tags) {
				if (loc.getTags().contains(t) && !locations.contains(loc)) {
					locations.add(loc);
				}
			}
		}
		
		return locations;
	}