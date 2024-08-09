public void validate(Object obj, Errors errors) {
		OrderType orderType = (OrderType)obj;
		if (orderType == null) {
			errors.rejectValue("orderType", "error.general", null, "Value required.");
		}
		else {
			if (orderType.getName() == null || orderType.getName().equals("")) {
				errors.rejectValue("name", "error.general.name");
			}
			if (orderType.getDescription() == null || orderType.getDescription().equals("")) {
				errors.rejectValue("description", "error.general.description");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}