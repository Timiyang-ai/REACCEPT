public void validate(Object obj, Errors errors) {
		Order order = (Order)obj;
		if (order == null) {
			errors.rejectValue("order", "error.general");
		}
		else {
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		}
		//log.debug("errors: " + errors.getAllErrors().toString());
	}