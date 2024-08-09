public void validate(Object obj, Errors errors) {
		Location location = (Location) obj;
		if (location == null) {
			errors.rejectValue("location", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			
			if (location.isRetired()) {
				if (!StringUtils.hasLength(location.getRetireReason())) {
					location.setRetired(false); // so that the jsp page displays properly again
					errors.rejectValue("retireReason", "error.null");
				}
			}
		}
	}