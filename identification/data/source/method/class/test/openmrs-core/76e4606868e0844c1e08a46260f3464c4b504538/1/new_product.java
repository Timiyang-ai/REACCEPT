public Location getDefaultLocation() throws APIException {
		Location location = null;
		String locationGP = Context.getAdministrationService().getGlobalProperty(
		    OpenmrsConstants.GLOBAL_PROPERTY_DEFAULT_LOCATION_NAME);
		
		if (StringUtils.hasText(locationGP))
			location = getLocation(locationGP);
		
		//Try to look up 'Unknown Location' in case the global property is something else
		if (location == null && (!StringUtils.hasText(locationGP) || !locationGP.equalsIgnoreCase("Unknown Location")))
			location = getLocation("Unknown Location");
		
		// If Unknown Location does not exist, try Unknown if the global property was different
		if (location == null && (!StringUtils.hasText(locationGP) || !locationGP.equalsIgnoreCase("Unknown"))) {
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