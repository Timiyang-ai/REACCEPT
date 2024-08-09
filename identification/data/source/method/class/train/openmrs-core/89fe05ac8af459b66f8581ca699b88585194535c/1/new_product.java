public void validate(Object obj, Errors errors) {
		ConceptClass cc = (ConceptClass) obj;
		if (cc == null) {
			errors.rejectValue("conceptClass", "error.general");
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localizedName.unlocalizedValue",
		    "LocalizedName.unlocalizedName.empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localizedDescription.unlocalizedValue",
			    "LocalizedDescription.unlocalizedDescription.empty");
		}
	}