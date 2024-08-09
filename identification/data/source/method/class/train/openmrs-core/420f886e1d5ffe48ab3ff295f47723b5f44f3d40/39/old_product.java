public void validate(Object obj, Errors errors) {
		Location location = (Location) obj;
		if (location == null) {
			errors.rejectValue("location", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			
			if (location.isRetired() && !StringUtils.hasLength(location.getRetireReason())) {
				location.setRetired(false); // so that the jsp page displays
				// properly again
				errors.rejectValue("retireReason", "error.null");
			}
			
			Location exist = Context.getLocationService().getLocation(location.getName());
			if (exist != null && !exist.isRetired() && !OpenmrsUtil.nullSafeEquals(location.getUuid(), exist.getUuid())) {
				errors.rejectValue("name", "location.duplicate.name");
			}
			
			// Traverse all the way up (down?) to the root and check if it
			// equals the root.
			Location root = location;
			while (root.getParentLocation() != null) {
				root = root.getParentLocation();
				if (root.equals(location)) { // Have gone in a circle
					errors.rejectValue("parentLocation", "Location.parentLocation.error");
					break;
				}
			}
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "name", "description", "address1", "address2",
			    "cityVillage", "stateProvince", "country", "postalCode", "latitude", "longitude", "countyDistrict",
			    "address3", "address4", "address5", "address6", "retireReason");
		}
		
		super.validateAttributes(location, errors, Context.getLocationService().getAllLocationAttributeTypes());
	}