public void validate(Object obj, Errors errors) {
		Form form = (Form) obj;
		if (form == null) {
			errors.rejectValue("form", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.name");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "version", "error.null");
			
			if (form.getVersion() != null && !form.getVersion().matches("^\\d.*$"))
				errors.rejectValue("version", "Form.version.invalid");
			
			if (form.isRetired()) {
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retireReason", "general.retiredReason.empty");
			}
		}
	}