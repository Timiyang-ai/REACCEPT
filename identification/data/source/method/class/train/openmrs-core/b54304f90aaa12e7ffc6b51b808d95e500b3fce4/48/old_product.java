public static Location getDefaultLocation() {
		if (defaultLocation == null && Context.isSessionOpen())
			defaultLocation = Context.getLocationService().getDefaultLocation();
		
		return defaultLocation;
	}