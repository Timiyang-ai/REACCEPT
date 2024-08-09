public void validate(Object obj, Errors errors) {
		FieldType fieldType = (FieldType) obj;
		if (fieldType == null) {
			errors.rejectValue("fieldType", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localizedName.unlocalizedValue",
			    "LocalizedName.unlocalizedName.empty");
		}
	}