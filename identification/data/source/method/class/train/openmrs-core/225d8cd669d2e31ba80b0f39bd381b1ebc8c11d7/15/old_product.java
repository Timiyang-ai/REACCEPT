public void validate(Object obj, Errors errors) {
		if (!ValidateUtil.isValidationOn()) {
			return;
		}
		
		PersonAttributeType patObj = (PersonAttributeType) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "PersonAttributeType.error.nameEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
		PersonService ps = Context.getPersonService();
		PersonAttributeType pat = ps.getPersonAttributeTypeByName(patObj.getName());
		if (pat != null && !pat.getPersonAttributeTypeId().equals(patObj.getPersonAttributeTypeId())) {
			errors.rejectValue("name", "PersonAttributeType.error.nameAlreadyInUse");
		}
	}