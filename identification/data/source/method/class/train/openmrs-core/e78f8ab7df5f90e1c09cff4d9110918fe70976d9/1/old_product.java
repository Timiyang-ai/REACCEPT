public static void validate(Object obj, Errors errors) {
		if (ValidateUtil.isValidationOn()) {
			Context.getAdministrationService().validate(obj, errors);
		}
	}