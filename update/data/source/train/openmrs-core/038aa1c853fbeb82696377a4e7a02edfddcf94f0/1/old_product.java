public void validate(Object obj, Errors errors) {
		PersonAttributeType patObj = (PersonAttributeType) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "PersonAttributeType.error.nameEmpty");
		PersonService ps = Context.getPersonService();
		PersonAttributeType pat = ps.getPersonAttributeTypeByName(patObj.getName());
		if (pat != null && !pat.getUuid().equals(patObj.getUuid())) {
			errors.rejectValue("name", "PersonAttributeType.error.nameAlreadyInUse");
		}
	}