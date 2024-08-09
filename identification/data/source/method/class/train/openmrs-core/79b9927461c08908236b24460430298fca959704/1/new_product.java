public static Boolean isInHierarchy(Location location, Location root) {
		if (root == null) {
			return false;
		}
		while (true) {
			if (location == null) {
				return false;
			} else if (root.equals(location)) {
				return true;
			}
			location = location.getParentLocation();
		}
	}