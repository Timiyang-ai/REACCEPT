public Integer resolveLocationId(String[] pl) throws HL7Exception {
		// TODO: Get rid of hack that allows first component to be an integer location.location_id
		String pointOfCare = pl[0];
		String facility = null;
		if (pl.length >= 4) {
			facility = pl[3];
		}

		// HACK: try to treat the first component (which should be "Point of Care" as an internal openmrs location_id 
		try {
			Integer locationId = new Integer(pointOfCare);
			Location l = context.getEncounterService().getLocation(locationId);
			return l == null ? null : l.getLocationId(); 
		} catch (Exception ex) {
			if (facility == null) { // we have no tricks left up our sleeve, so throw an exception
				throw new HL7Exception("Error trying to treat PL.pointOfCare '" + pointOfCare + "' as a location.location_id", ex);
			}
		}
		
		// Treat the 4th component "Facility" as location.name
		try {
			Location l = context.getEncounterService().getLocationByName(facility);
			if (l == null) {
				log.debug("Couldn't find a location named '" + facility + "'");
			}
			return l == null ? null : l.getLocationId();
		} catch (Exception ex) {
			log.error("Error trying to treat PL.facility '" + facility + "' as a location.name", ex);
			return null;
		}
	}