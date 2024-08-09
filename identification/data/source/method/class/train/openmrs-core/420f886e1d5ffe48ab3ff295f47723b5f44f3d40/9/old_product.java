public void validate(Object obj, Errors errors) {
		Location location = (Location) obj;
		if (location == null) {
			errors.rejectValue("location", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			
			if (location.isRetired()) {
				if (!StringUtils.hasLength(location.getRetireReason())) {
					location.setRetired(false); // so that the jsp page displays
					// properly again
					errors.rejectValue("retireReason", "error.null");
				}
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
		}
		
		super.validateAttributes(location, errors, Context.getLocationService().getAllLocationAttributeTypes());
	}