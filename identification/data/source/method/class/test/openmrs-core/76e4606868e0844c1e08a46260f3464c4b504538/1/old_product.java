public Location getDefaultLocation() throws APIException {
		
		// TODO The name of the default location should be configured using global properties 
		Location location = getLocation("Unknown Location");
		
		// If Unknown Location does not exist, try Unknown
		if (location == null) {
			location = getLocation("Unknown");
		}
		
		// If neither exist, get the first available location
		if (location == null) {
			location = getLocation(Integer.valueOf(1));
		}
		
		// TODO Figure out if we should/could throw an exception if there's  
		// no location to fall back on.
		//if (location == null) { 
		//	throw new APIException("Default location does not exist");
		//}
		
		return location;
	}