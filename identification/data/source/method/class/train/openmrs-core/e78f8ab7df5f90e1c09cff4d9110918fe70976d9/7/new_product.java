public static void validate(Object obj, Errors errors) {
		if (disableValidation) {
			return;
		}

		Context.getAdministrationService().validate(obj, errors);
	}