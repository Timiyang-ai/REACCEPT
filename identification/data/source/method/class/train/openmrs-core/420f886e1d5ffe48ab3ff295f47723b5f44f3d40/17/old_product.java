public void validate(Object obj, Errors errors) {
		Privilege privilege = (Privilege) obj;
		if (privilege == null) {
			errors.rejectValue("privilege", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "privilege", "error.privilege");
			ValidateUtil.validateFieldLengths(errors, obj.getClass(), "privilege", "description");
		}
	}