public Set<Location> getDescendantLocations(boolean includeRetired) {
		Set<Location> result = new HashSet<Location>();
		
		for (Location childLocation : getChildLocations()) {
			if (!childLocation.getRetired() || includeRetired) {
				result.add(childLocation);
				result.addAll(childLocation.getDescendantLocations(includeRetired));
			}
		}
		return result;
	}