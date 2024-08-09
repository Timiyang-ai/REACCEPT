public void validate(Object obj, Errors errors) {
		Location location = (Location) obj;
		if (location == null) {
			errors.rejectValue("location", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
			
			if (location.isRetired()) {
				if (!StringUtils.hasLength(location.getRetireReason())) {
					location.setRetired(false); // so that the jsp page displays properly again
					errors.rejectValue("retireReason", "error.null");
				}
			}
		}
		
		super.validateAttributes(location, errors, Context.getLocationService().getAllLocationAttributeTypes());
	}