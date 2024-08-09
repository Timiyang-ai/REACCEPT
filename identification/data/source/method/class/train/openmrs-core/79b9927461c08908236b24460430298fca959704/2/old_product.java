public static Boolean isInHierarchy(Location location, Location root) {
		if (location == null || root == null)
			return false;
		if (root.equals(location))
			return true;
		if (root.getChildLocations() != null) {
			for (Location l : root.getChildLocations())
				return isInHierarchy(location, l);
		}
		
		return false;
	}