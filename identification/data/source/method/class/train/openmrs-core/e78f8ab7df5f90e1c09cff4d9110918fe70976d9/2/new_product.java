public static void validate(Object obj, Errors errors) {
		Context.getAdministrationService().validate(obj, errors);
	}