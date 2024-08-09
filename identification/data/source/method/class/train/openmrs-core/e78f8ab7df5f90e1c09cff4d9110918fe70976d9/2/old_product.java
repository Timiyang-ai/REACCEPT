public static void validate(Object obj, Errors errors) {
		for (Validator validator : getValidators(obj)) {
			validator.validate(obj, errors);
		}
	}