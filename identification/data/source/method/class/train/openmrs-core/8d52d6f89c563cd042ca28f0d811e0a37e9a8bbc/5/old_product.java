public void validate(Object obj, Errors errors) {
		OrderType orderType = (OrderType)obj;
		if (orderType == null) {
			errors.rejectValue("orderType", "general.error");
		}
		else {
			if (orderType.getName() == null || orderType.getName().equals("")) {
				errors.rejectValue("name", "general.name.error");
			}
			if (orderType.getDescription() == null || orderType.getDescription().equals("")) {
				errors.rejectValue("description", "general.description.error");
			}
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}