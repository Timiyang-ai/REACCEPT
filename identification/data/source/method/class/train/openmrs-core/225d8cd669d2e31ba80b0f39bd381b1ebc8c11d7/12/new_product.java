public void validate(Object obj, Errors errors) throws APIException {
		if (log.isDebugEnabled()) {
			log.debug(this.getClass().getName() + ".validate...");
		}
		
		if (obj == null || !(obj instanceof Field)) {
			throw new IllegalArgumentException("The parameter obj should not be null and must be of type " + Field.class);
		}
		
		Field field = (Field) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.null", "Field name is required");
		if (field.getSelectMultiple() == null) {
			errors.rejectValue("selectMultiple", "error.general");
		}
		if (field.getRetired() == null) {
			errors.rejectValue("retired", "error.general");
		}
	}