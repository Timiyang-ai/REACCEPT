public void validate(Object obj, Errors errors) {
		OrderType orderType = (OrderType) obj;
		if (orderType == null) {
			errors.rejectValue("orderType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localizedName.unlocalizedValue",
			    "LocalizedName.unlocalizedName.empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}